import java.util.*;

class Book {
    String bookId, title, author, category;
    boolean isIssued;
    static int totalBooks = 0;

    Book(String id, String t, String a, String c) {
        bookId = id; title = t; author = a; category = c; 
        isIssued = false; totalBooks++;
    }
}

class Member {
    String memberId, name, type;
    ArrayList<Book> booksIssued = new ArrayList<>();
    double totalFines = 0;
    static int totalMembers = 0, maxBooksAllowed = 3;
    static double finePerDay = 5;

    Member(String id, String n, String t) {
        memberId = id; name = n; type = t; totalMembers++;
    }

    void issueBook(Book b) {
        if (!b.isIssued && booksIssued.size() < maxBooksAllowed) {
            booksIssued.add(b); b.isIssued = true;
            System.out.println(name + " issued " + b.title);
        } else System.out.println("Cannot issue book!");
    }

    void returnBook(Book b, int overdueDays) {
        if (booksIssued.remove(b)) {
            b.isIssued = false;
            if (overdueDays > 0) totalFines += overdueDays * finePerDay;
            System.out.println(name + " returned " + b.title);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book("B1", "Java Basics", "Author A", "Programming");
        Book b2 = new Book("B2", "DSA", "Author B", "CS");
        Member m1 = new Member("M1", "Alice", "Student");
        Member m2 = new Member("M2", "Bob", "Faculty");

        m1.issueBook(b1);
        m2.issueBook(b2);
        m1.returnBook(b1, 2); // returned after 2 days late

        System.out.println("Total Books: " + Book.totalBooks);
        System.out.println("Total Members: " + Member.totalMembers);
        System.out.println(m1.name + " fines: " + m1.totalFines);
    }
}
