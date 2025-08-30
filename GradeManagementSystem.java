import java.util.*;

class Subject {
    String code, name, instructor;
    int credits;
    Subject(String c, String n, int cr, String i) {
        code = c;
        name = n;
        credits = cr;
        instructor = i;
    }
}

class Student {
    String id, name, className;
    String[] subjects;
    double[][] marks;
    double gpa;
    static int totalStudents = 0;
    static String schoolName = "GenZ School";
    static String[] gradingScale = {"A:90-100", "B:75-89", "C:60-74", "D:40-59", "F:<40"};
    static double passPercentage = 40;

    Student(String id, String n, String cls, String[] subs) {
        this.id = id;
        name = n;
        className = cls;
        subjects = subs;
        marks = new double[subs.length][5]; // up to 5 exams per subject
        totalStudents++;
    }

    void addMarks(String subject, double score) {
        for (int i = 0; i < subjects.length; i++)
            if (subjects[i].equals(subject)) {
                for (int j = 0; j < marks[i].length; j++)
                    if (marks[i][j] == 0) {
                        marks[i][j] = score;
                        return;
                    }
            }
    }

    void calculateGPA() {
        double total = 0;
        int count = 0;
        for (double[] sub : marks)
            for (double m : sub)
                if (m > 0) {
                    total += m;
                    count++;
                }
        double avg = (count == 0) ? 0 : total / count;
        gpa = avg / 25; // GPA on scale of 4
    }

    void generateReportCard() {
        calculateGPA();
        System.out.println("\nReport Card - " + name + " (" + className + ")");
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            int c = 0;
            for (double m : marks[i])
                if (m > 0) {
                    sum += m;
                    c++;
                }
            double avg = (c == 0) ? 0 : sum / c;
            System.out.println(subjects[i] + ": " + avg + " -> " + grade(avg));
        }
        System.out.println("GPA: " + String.format("%.2f", gpa) + " | Promotion: " + (checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
    }

    boolean checkPromotionEligibility() {
        double total = 0;
        int count = 0;
        for (double[] sub : marks)
            for (double m : sub)
                if (m > 0) {
                    total += m;
                    count++;
                }
        return (count == 0) ? false : (total / count) >= passPercentage;
    }

    static String grade(double avg) {
        if (avg >= 90) return "A";
        if (avg >= 75) return "B";
        if (avg >= 60) return "C";
        if (avg >= 40) return "D";
        return "F";
    }

    // === Static Reporting ===
    static void calculateClassAverage(Student[] studs, String cls) {
        double sum = 0;
        int count = 0;
        for (Student s : studs)
            if (s.className.equals(cls)) {
                s.calculateGPA();
                sum += s.gpa;
                count++;
            }
        System.out.println("Class " + cls + " Avg GPA: " + (count == 0 ? 0 : sum / count));
    }

    static void getTopPerformers(Student[] studs, int n) {
        Arrays.sort(studs, (a, b) -> Double.compare(b.gpa, a.gpa));
        System.out.println("\nTop " + n + " Performers:");
        for (int i = 0; i < Math.min(n, studs.length); i++)
            System.out.println(studs[i].name + " GPA:" + String.format("%.2f", studs[i].gpa));
    }

    static void generateSchoolReport(Student[] studs) {
        System.out.println("\n--- School Report (" + schoolName + ") ---");
        Map<String, Integer> grades = new HashMap<>();
        for (Student s : studs) {
            s.calculateGPA();
            String g = grade(s.gpa * 25);
            grades.put(g, grades.getOrDefault(g, 0) + 1);
        }
        System.out.println("Grade Distribution: " + grades);
    }
}

public class GradeManagementSystem {
    public static void main(String[] args) {
        // Subjects
        Subject math = new Subject("M101", "Math", 4, "Mr.X");
        Subject eng = new Subject("E101", "English", 3, "Ms.Y");
        String[] subs = {math.name, eng.name};

        // Students
        Student s1 = new Student("S1", "Alice", "10A", subs);
        Student s2 = new Student("S2", "Bob", "10A", subs);
        Student s3 = new Student("S3", "Charlie", "10B", subs);

        // Marks
        s1.addMarks("Math", 95);
        s1.addMarks("English", 88);
        s2.addMarks("Math", 70);
        s2.addMarks("English", 60);
        s3.addMarks("Math", 40);
        s3.addMarks("English", 35);

        // Reports
        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        Student[] studs = {s1, s2, s3};
        Student.calculateClassAverage(studs, "10A");
        Student.getTopPerformers(studs, 2);
        Student.generateSchoolReport(studs);
    }
}