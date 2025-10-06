class HotelBookingSystem {
    private static final double STANDARD_ROOM_PRICE = 100.0;
    private static final double DELUXE_ROOM_PRICE = 150.0;
    private static final double SUITE_ROOM_PRICE = 250.0;
    
    // Standard booking
    public double calculatePrice(String roomType, int nights) {
        double basePrice = getRoomPrice(roomType);
        double totalPrice = basePrice * nights;
        
        System.out.println("=== STANDARD BOOKING ===");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Base Price per night: $" + basePrice);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("========================");
        
        return totalPrice;
    }
    
    // Seasonal booking
    public double calculatePrice(String roomType, int nights, double seasonalMultiplier) {
        double basePrice = getRoomPrice(roomType);
        double totalPrice = basePrice * nights * seasonalMultiplier;
        double seasonalIncrease = totalPrice - (basePrice * nights);
        
        System.out.println("=== SEASONAL BOOKING ===");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Base Price per night: $" + basePrice);
        System.out.println("Seasonal Multiplier: " + seasonalMultiplier + "x");
        System.out.println("Seasonal Increase: $" + seasonalIncrease);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("========================");
        
        return totalPrice;
    }
    
    // Corporate booking
    public double calculatePrice(String roomType, int nights, double corporateDiscount, boolean mealPackage) {
        double basePrice = getRoomPrice(roomType);
        double subtotal = basePrice * nights;
        double discountAmount = subtotal * (corporateDiscount / 100);
        double mealCost = mealPackage ? nights * 30.0 : 0;
        double totalPrice = subtotal - discountAmount + mealCost;
        
        System.out.println("=== CORPORATE BOOKING ===");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Base Price: $" + subtotal);
        System.out.println("Corporate Discount (" + corporateDiscount + "%): -$" + discountAmount);
        System.out.println("Meal Package: " + (mealPackage ? "$" + mealCost : "None"));
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("========================");
        
        return totalPrice;
    }
    
    // Wedding package
    public double calculatePrice(String roomType, int nights, int guestCount, double decorationFee, String cateringOption) {
        double basePrice = getRoomPrice(roomType);
        double roomCost = basePrice * nights;
        double cateringCost = getCateringCost(cateringOption, guestCount);
        double totalPrice = roomCost + decorationFee + cateringCost;
        
        System.out.println("=== WEDDING PACKAGE ===");
        System.out.println("Room Type: " + roomType);
        System.out.println("Nights: " + nights);
        System.out.println("Guest Count: " + guestCount);
        System.out.println("Room Cost: $" + roomCost);
        System.out.println("Decoration Fee: $" + decorationFee);
        System.out.println("Catering (" + cateringOption + "): $" + cateringCost);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("========================");
        
        return totalPrice;
    }
    
    private double getRoomPrice(String roomType) {
        switch (roomType.toLowerCase()) {
            case "standard": return STANDARD_ROOM_PRICE;
            case "deluxe": return DELUXE_ROOM_PRICE;
            case "suite": return SUITE_ROOM_PRICE;
            default: return STANDARD_ROOM_PRICE;
        }
    }
    
    private double getCateringCost(String option, int guests) {
        switch (option.toLowerCase()) {
            case "basic": return guests * 25.0;
            case "premium": return guests * 50.0;
            case "luxury": return guests * 80.0;
            default: return guests * 25.0;
        }
    }
}

public class HotelBookingDemo {
    public static void main(String[] args) {
        HotelBookingSystem hotel = new HotelBookingSystem();
        
        // Standard booking
        hotel.calculatePrice("Deluxe", 3);
        
        // Seasonal booking
        hotel.calculatePrice("Suite", 2, 1.5);
        
        // Corporate booking
        hotel.calculatePrice("Standard", 5, 15.0, true);
        
        // Wedding package
        hotel.calculatePrice("Suite", 2, 50, 500.0, "Premium");
    }
}
