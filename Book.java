public class Book {
    String title;
    String author;
    double price;

    public Book() {
        this.title = "I1";
        this.author = "Nainesh";
        this.price = 1000.0;
    }

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println();
    }

    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("1984", "George Orwell", 9.99);

        System.out.println("Book 1:");
        book1.display();

        System.out.println("Book 2:");
        book2.display();
    }
}