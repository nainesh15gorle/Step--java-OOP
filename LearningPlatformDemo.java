import java.time.LocalDate;

abstract class Course {
    protected String title;
    protected String instructor;
    protected LocalDate enrollmentDate;
    
    public Course(String title, String instructor, LocalDate enrollmentDate) {
        this.title = title;
        this.instructor = instructor;
        this.enrollmentDate = enrollmentDate;
    }
    
    public void displayBasicInfo() {
        System.out.println("Course: " + title);
        System.out.println("Instructor: " + instructor);
        System.out.println("Enrolled: " + enrollmentDate);
    }
    
    // Abstract method to be overridden
    public abstract void displayProgress();
}

class VideoCourse extends Course {
    private double completionPercentage;
    private int watchTimeMinutes;
    
    public VideoCourse(String title, String instructor, LocalDate enrollmentDate, 
                      double completionPercentage, int watchTimeMinutes) {
        super(title, instructor, enrollmentDate);
        this.completionPercentage = completionPercentage;
        this.watchTimeMinutes = watchTimeMinutes;
    }
    
    @Override
    public void displayProgress() {
        displayBasicInfo();
        System.out.println("=== VIDEO COURSE PROGRESS ===");
        System.out.println("Completion: " + completionPercentage + "%");
        System.out.println("Watch Time: " + watchTimeMinutes + " minutes");
        System.out.println("Status: " + (completionPercentage >= 100 ? "Completed" : "In Progress"));
        System.out.println("=============================\n");
    }
}

class InteractiveCourse extends Course {
    private int quizScore;
    private int projectsCompleted;
    private int totalProjects;
    
    public InteractiveCourse(String title, String instructor, LocalDate enrollmentDate,
                           int quizScore, int projectsCompleted, int totalProjects) {
        super(title, instructor, enrollmentDate);
        this.quizScore = quizScore;
        this.projectsCompleted = projectsCompleted;
        this.totalProjects = totalProjects;
    }
    
    @Override
    public void displayProgress() {
        displayBasicInfo();
        System.out.println("=== INTERACTIVE COURSE PROGRESS ===");
        System.out.println("Quiz Score: " + quizScore + "/100");
        System.out.println("Projects: " + projectsCompleted + "/" + totalProjects + " completed");
        System.out.println("Project Progress: " + (projectsCompleted * 100 / totalProjects) + "%");
        System.out.println("Grade: " + getGrade(quizScore));
        System.out.println("===================================\n");
    }
    
    private String getGrade(int score) {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }
}

class ReadingCourse extends Course {
    private int pagesRead;
    private int totalPages;
    private int notesCount;
    
    public ReadingCourse(String title, String instructor, LocalDate enrollmentDate,
                        int pagesRead, int totalPages, int notesCount) {
        super(title, instructor, enrollmentDate);
        this.pagesRead = pagesRead;
        this.totalPages = totalPages;
        this.notesCount = notesCount;
    }
    
    @Override
    public void displayProgress() {
        displayBasicInfo();
        System.out.println("=== READING COURSE PROGRESS ===");
        System.out.println("Pages Read: " + pagesRead + "/" + totalPages);
        System.out.println("Reading Progress: " + (pagesRead * 100 / totalPages) + "%");
        System.out.println("Notes Taken: " + notesCount);
        System.out.println("Average Notes per Page: " + (pagesRead > 0 ? notesCount / pagesRead : 0));
        System.out.println("===============================\n");
    }
}

class CertificationCourse extends Course {
    private int examAttempts;
    private boolean certified;
    private int lastScore;
    
    public CertificationCourse(String title, String instructor, LocalDate enrollmentDate,
                             int examAttempts, boolean certified, int lastScore) {
        super(title, instructor, enrollmentDate);
        this.examAttempts = examAttempts;
        this.certified = certified;
        this.lastScore = lastScore;
    }
    
    @Override
    public void displayProgress() {
        displayBasicInfo();
        System.out.println("=== CERTIFICATION COURSE PROGRESS ===");
        System.out.println("Exam Attempts: " + examAttempts);
        System.out.println("Last Score: " + lastScore + "%");
        System.out.println("Certification Status: " + (certified ? "CERTIFIED" : "NOT CERTIFIED"));
        System.out.println("Next Attempt: " + (certified ? "N/A" : "Available"));
        System.out.println("=====================================\n");
    }
}

public class LearningPlatformDemo {
    public static void main(String[] args) {
        Course[] courses = {
            new VideoCourse("Java Programming", "Dr. Smith", LocalDate.of(2024, 1, 15), 75.5, 320),
            new InteractiveCourse("Web Development", "Prof. Johnson", LocalDate.of(2024, 2, 1), 88, 3, 5),
            new ReadingCourse("Data Structures", "Dr. Wilson", LocalDate.of(2024, 1, 20), 150, 200, 45),
            new CertificationCourse("AWS Cloud", "Amazon", LocalDate.of(2024, 3, 1), 2, true, 92)
        };
        
        for (Course course : courses) {
            course.displayProgress();
        }
    }
}
