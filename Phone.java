class Phone {
    protected String brand;
    protected String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone constructor called");
    }
}

class SmartPhone extends Phone {
    protected String operatingSystem;

    public SmartPhone(String brand, String model, String os) {
        super(brand, model);
        this.operatingSystem = os;
        System.out.println("SmartPhone constructor called");
    }

    public void showInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("OS: " + operatingSystem);
    }

    public static void main(String[] args) {
        SmartPhone s1 = new SmartPhone("Samsung", "Galaxy S24", "Android");
        s1.showInfo();
    }
}
