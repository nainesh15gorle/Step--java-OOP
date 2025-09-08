class Workout {
    // Fields
    private String activityName;
    private int durationInMinutes;
    private int caloriesBurned;
    
    // Constants for default values
    private static final String DEFAULT_ACTIVITY = "Walking";
    private static final int DEFAULT_DURATION = 30;
    private static final int DEFAULT_CALORIES = 100;
    private static final int CALORIES_PER_MINUTE = 5;
    
    // 1. Default constructor → "Walking", 30 mins, 100 calories
    public Workout() {
        this.activityName = DEFAULT_ACTIVITY;
        this.durationInMinutes = DEFAULT_DURATION;
        this.caloriesBurned = DEFAULT_CALORIES;
    }
    
    // 2. Constructor with activity name → assigns default duration
    public Workout(String activityName) {
        this(); // Call default constructor first
        this.activityName = activityName;
        // Recalculate calories based on default duration for the new activity
        this.caloriesBurned = calculateCalories(DEFAULT_DURATION);
    }
    
    // 3. Constructor with activity and duration → calculate caloriesBurned = duration × 5
    public Workout(String activityName, int durationInMinutes) {
        this.activityName = activityName;
        if (durationInMinutes > 0) {
            this.durationInMinutes = durationInMinutes;
        } else {
            System.out.println("⚠️  Invalid duration for " + activityName + ". Setting to default (" + DEFAULT_DURATION + " mins).");
            this.durationInMinutes = DEFAULT_DURATION;
        }
        this.caloriesBurned = calculateCalories(this.durationInMinutes);
    }
    
    // Helper method to calculate calories
    private int calculateCalories(int duration) {
        return duration * CALORIES_PER_MINUTE;
    }
    
    // Method to display workout details
    public void displayWorkout() {
        System.out.println("🏃‍♂️ Workout Summary:");
        System.out.println("─────────────────────────");
        System.out.println("Activity:      " + activityName);
        System.out.println("Duration:      " + durationInMinutes + " minutes");
        System.out.println("Calories:      " + caloriesBurned + " kcal");
        System.out.println("─────────────────────────");
        System.out.println();
    }
    
    // Method to get workout intensity
    public String getIntensity() {
        if (durationInMinutes < 20) {
            return "Light";
        } else if (durationInMinutes < 45) {
            return "Moderate";
        } else {
            return "Intense";
        }
    }
    
    // Method to add more minutes to the workout
    public void addDuration(int additionalMinutes) {
        if (additionalMinutes > 0) {
            durationInMinutes += additionalMinutes;
            caloriesBurned = calculateCalories(durationInMinutes);
            System.out.println("⏱️  Added " + additionalMinutes + " minutes to " + activityName);
        } else {
            System.out.println("⚠️  Cannot add negative or zero minutes.");
        }
    }
    
    // Getters
    public String getActivityName() {
        return activityName;
    }
    
    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    
    public int getCaloriesBurned() {
        return caloriesBurned;
    }
    
    // Setters
    public void setActivityName(String activityName) {
        this.activityName = activityName;
        // Recalculate calories when activity changes
        this.caloriesBurned = calculateCalories(durationInMinutes);
    }
    
    public void setDurationInMinutes(int durationInMinutes) {
        if (durationInMinutes > 0) {
            this.durationInMinutes = durationInMinutes;
            this.caloriesBurned = calculateCalories(durationInMinutes);
        } else {
            System.out.println("⚠️  Duration must be positive. No change made.");
        }
    }
    
    // Static method to display fitness tips
    public static void displayFitnessTips() {
        System.out.println("💪 Fitness Tips:");
        System.out.println("• Aim for at least 30 minutes of activity daily");
        System.out.println("• Mix cardio and strength training");
        System.out.println("• Stay hydrated during workouts");
        System.out.println("• Listen to your body and rest when needed");
        System.out.println();
    }
}

// Main class to test the Fitness Tracker App
public class FitnessTrackerApp {
    public static void main(String[] args) {
        System.out.println("📊 Welcome to Fitness Tracker App! 📊\n");
        
        // Display fitness tips
        Workout.displayFitnessTips();
        
        // Create workouts using different constructors
        Workout[] workouts = {
            // 1. Default constructor
            new Workout(),
            
            // 2. Constructor with activity name only
            new Workout("Running"),
            new Workout("Cycling"),
            
            // 3. Constructor with activity and duration
            new Workout("Swimming", 45),
            new Workout("Yoga", 60),
            new Workout("Weight Training", 75),
            new Workout("HIIT", 20),
            new Workout("Dancing", 30)
        };
        
        // Display all workouts
        System.out.println("📋 Today's Workout Sessions:");
        System.out.println("─────────────────────────────────────");
        
        for (int i = 0; i < workouts.length; i++) {
            System.out.println("Session " + (i + 1) + ":");
            workouts[i].displayWorkout();
        }
        
        // Perform some operations on workouts
        System.out.println("🔄 Modifying Workouts:");
        System.out.println("─────────────────────────────────────");
        
        // Add more duration to some workouts
        workouts[0].addDuration(15); // Add 15 minutes to walking
        workouts[1].addDuration(10); // Add 10 minutes to running
        
        // Change activity type
        workouts[2].setActivityName("Mountain Biking");
        System.out.println("🚴 Changed activity to: Mountain Biking");
        
        System.out.println();
        
        // Display updated workouts
        System.out.println("📊 Updated Workout Sessions:");
        System.out.println("─────────────────────────────────────");
        
        for (int i = 0; i < 4; i++) { // Show first 4 updated workouts
            System.out.println("Session " + (i + 1) + " (Updated):");
            workouts[i].displayWorkout();
        }
        
        // Calculate and display workout statistics
        displayWorkoutStatistics(workouts);
        
        // Show intensity levels
        System.out.println("🔥 Workout Intensity Analysis:");
        System.out.println("─────────────────────────────────────");
        
        for (Workout workout : workouts) {
            System.out.println(workout.getActivityName() + ": " + workout.getIntensity() + 
                             " (" + workout.getDurationInMinutes() + " mins)");
        }
        
        System.out.println();
        
        // Create a custom workout session
        System.out.println("🎯 Creating Custom Workout Session:");
        Workout customWorkout = new Workout("Boxing", 50);
        customWorkout.displayWorkout();
        
        // Test edge cases
        System.out.println("⚠️  Testing Edge Cases:");
        Workout testWorkout = new Workout("Test", -10); // Should handle gracefully
        testWorkout.setDurationInMinutes(-5); // Should not change duration
        testWorkout.displayWorkout();
    }
    
    // Helper method to display workout statistics
    private static void displayWorkoutStatistics(Workout[] workouts) {
        System.out.println("📈 Workout Statistics:");
        System.out.println("─────────────────────────────────────");
        
        int totalDuration = 0;
        int totalCalories = 0;
        int maxDuration = 0;
        String longestWorkout = "";
        
        for (Workout workout : workouts) {
            totalDuration += workout.getDurationInMinutes();
            totalCalories += workout.getCaloriesBurned();
            
            if (workout.getDurationInMinutes() > maxDuration) {
                maxDuration = workout.getDurationInMinutes();
                longestWorkout = workout.getActivityName();
            }
        }
        
        double averageDuration = (double) totalDuration / workouts.length;
        double averageCalories = (double) totalCalories / workouts.length;
        
        System.out.println("Total Workouts: " + workouts.length);
        System.out.println("Total Duration: " + totalDuration + " minutes");
        System.out.println("Total Calories: " + totalCalories + " kcal");
        System.out.println("Average Duration: " + String.format("%.1f", averageDuration) + " minutes");
        System.out.println("Average Calories: " + String.format("%.1f", averageCalories) + " kcal per workout");
        System.out.println("Longest Workout: " + longestWorkout + " (" + maxDuration + " mins)");
        System.out.println("─────────────────────────────────────");
        System.out.println();
    }
    