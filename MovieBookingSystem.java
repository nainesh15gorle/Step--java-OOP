class MovieTicket {
    // Fields
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;
    
    // Constants
    private static final String DEFAULT_MOVIE = "Unknown";
    private static final String DEFAULT_THEATRE = "PVR";
    private static final double DEFAULT_PRICE = 200.0;
    private static final int DEFAULT_SEAT = -1; // Indicates no seat assigned
    
    // 1. Default constructor → assigns "Unknown" movie
    public MovieTicket() {
        this.movieName = DEFAULT_MOVIE;
        this.theatreName = DEFAULT_THEATRE;
        this.seatNumber = DEFAULT_SEAT;
        this.price = DEFAULT_PRICE;
    }
    
    // 2. Constructor with movie name → assigns default price = 200
    public MovieTicket(String movieName) {
        this(); // Call default constructor first
        this.movieName = movieName;
    }
    
    // 3. Constructor with movie name and seat number → assigns default theatre "PVR"
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName); // Call constructor with movie name
        this.seatNumber = seatNumber;
        this.theatreName = DEFAULT_THEATRE;
    }
    
    // 4. Full constructor → sets all details
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }
    
    // Method to print ticket details
    public void printTicket() {
        System.out.println("🎬 Movie Ticket Details:");
        System.out.println("─────────────────────────");
        System.out.println("Movie:      " + movieName);
        System.out.println("Theatre:    " + theatreName);
        
        if (seatNumber == DEFAULT_SEAT) {
            System.out.println("Seat:       Not assigned");
        } else {
            System.out.println("Seat:       " + seatNumber);
        }
        
        System.out.println("Price:      ₹" + price);
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Getters
    public String getMovieName() {
        return movieName;
    }
    
    public String getTheatreName() {
        return theatreName;
    }
    
    public int getSeatNumber() {
        return seatNumber;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setters
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }
    
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}

// Main class to test the Movie Ticket System
public class MovieBookingSystem {
    public static void main(String[] args) {
        System.out.println("🎭 Welcome to Movie Ticket Booking System! 🎭\n");
        
        // Create tickets using different constructors
        MovieTicket[] tickets = {
            // 1. Default constructor
            new MovieTicket(),
            
            // 2. Constructor with movie name only
            new MovieTicket("Avengers: Endgame"),
            
            // 3. Constructor with movie name and seat number
            new MovieTicket("Joker", 15),
            
            // 4. Full constructor
            new MovieTicket("Dune: Part Two", "IMAX", 7, 350.0),
            
            // Additional examples
            new MovieTicket("Inception", 23),
            new MovieTicket("The Dark Knight", "Cinepolis", 12, 280.0)
        };
        
        // Print all tickets
        System.out.println("📋 Printing All Tickets:\n");
        for (int i = 0; i < tickets.length; i++) {
            System.out.println("Ticket #" + (i + 1) + ":");
            tickets[i].printTicket();
        }
        
        // Demonstrate some modifications
        System.out.println("🎫 After Seat Assignment and Price Update:\n");
        
        // Assign seat to the first ticket (default constructor)
        tickets[0].setSeatNumber(5);
        tickets[0].setMovieName("Interstellar");
        tickets[0].setPrice(250.0);
        System.out.println("Updated Ticket #1:");
        tickets[0].printTicket();
        
        // Show total booking summary
        System.out.println("💰 Booking Summary:");
        System.out.println("Total tickets: " + tickets.length);
        
        double totalRevenue = 0;
        for (MovieTicket ticket : tickets) {
            totalRevenue += ticket.getPrice();
        }
        
        System.out.println("Total revenue: ₹" + totalRevenue);
        System.out.println("Average ticket price: ₹" + (totalRevenue / tickets.length));
        System.out.println("\nThank you for using the Movie Ticket Booking System! Enjoy your movies! 🍿");
    }
}