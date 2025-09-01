public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType,
                         boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        if (batteryLevel < 0) {
            this.batteryLevel = 0;
        } else if (batteryLevel > 100) {
            this.batteryLevel = 100;
        } else {
            this.batteryLevel = batteryLevel;
        }
        if (sensitivity < 0.1) {
            this.sensitivity = 0.1;
        } else if (sensitivity > 3.0) {
            this.sensitivity = 3.0;
        } else {
            this.sensitivity = sensitivity;
        }
    }

    public GameController(String brand, String connectionType) {
        this(brand, connectionType, true, 100, 1.0);
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
        System.out.println();
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");

        GameController controller1 = new GameController();

        GameController controller2 = new GameController("ProGamer", "Bluetooth", false, 85, 2.5);
        
        GameController controller3 = new GameController("Speedy", "Wireless");

        System.out.println("Controller 1:");
        controller1.displayConfiguration();
        controller1.calibrateController();
        controller1.testVibration();

        System.out.println("\nController 2:");
        controller2.displayConfiguration();
        controller2.calibrateController();
        controller2.testVibration();

        System.out.println("\nController 3:");
        controller3.displayConfiguration();
        controller3.calibrateController();
        controller3.testVibration();

        System.out.println("\nComparing controller brands:");
        System.out.println("Controller 1 brand: " + controller1.controllerBrand);
        System.out.println("Controller 2 brand: " + controller2.controllerBrand);
        System.out.println("Controller 3 brand: " + controller3.controllerBrand);
    }
}