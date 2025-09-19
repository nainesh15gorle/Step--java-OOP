class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor: " + name);
    }
}

class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor: Intensity " + intensity);
    }
}

class RedColor extends PrimaryColor {
    protected String shade;

    public RedColor(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor constructor: Shade " + shade);
    }

    public static void main(String[] args) {
        RedColor red = new RedColor("Red", 100, "Crimson");
    }
}
