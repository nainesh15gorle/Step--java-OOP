abstract class Artwork {
    protected String title;
    protected String artist;
    protected double price;
    protected String dateCreated;
    protected boolean onDisplay;
    
    public Artwork(String title, String artist, double price, String dateCreated) {
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.dateCreated = dateCreated;
        this.onDisplay = false;
    }
    
    public void displayBasicInfo() {
        System.out.println("Title: " + title);
        System.out.println("Artist: " + artist);
        System.out.println("Price: $" + price);
        System.out.println("Created: " + dateCreated);
        System.out.println("On Display: " + (onDisplay ? "Yes" : "No"));
    }
    
    public abstract void displayArtworkDetails();
    
    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public boolean isOnDisplay() { return onDisplay; }
    public void setOnDisplay(boolean onDisplay) { this.onDisplay = onDisplay; }
}

class Painting extends Artwork {
    private String brushTechnique;
    private String colorPalette;
    private String frameType;
    private String canvasSize;
    
    public Painting(String title, String artist, double price, String dateCreated,
                   String brushTechnique, String colorPalette, String frameType, String canvasSize) {
        super(title, artist, price, dateCreated);
        this.brushTechnique = brushTechnique;
        this.colorPalette = colorPalette;
        this.frameType = frameType;
        this.canvasSize = canvasSize;
    }
    
    @Override
    public void displayArtworkDetails() {
        System.out.println("=== PAINTING DETAILS ===");
        displayBasicInfo();
        System.out.println("Brush Technique: " + brushTechnique);
        System.out.println("Color Palette: " + colorPalette);
        System.out.println("Frame Type: " + frameType);
        System.out.println("Canvas Size: " + canvasSize);
        System.out.println("========================\n");
    }
    
    // Painting-specific methods
    public String getBrushTechnique() { return brushTechnique; }
    public String getColorPalette() { return colorPalette; }
    public String getFrameType() { return frameType; }
    public String getCanvasSize() { return canvasSize; }
    
    public void changeFrame(String newFrame) {
        this.frameType = newFrame;
        System.out.println("Frame changed to: " + newFrame + " for painting: " + title);
    }
}

class Sculpture extends Artwork {
    private String material;
    private String dimensions;
    private String lightingRequirement;
    private double weight;
    
    public Sculpture(String title, String artist, double price, String dateCreated,
                    String material, String dimensions, String lightingRequirement, double weight) {
        super(title, artist, price, dateCreated);
        this.material = material;
        this.dimensions = dimensions;
        this.lightingRequirement = lightingRequirement;
        this.weight = weight;
    }
    
    @Override
    public void displayArtworkDetails() {
        System.out.println("=== SCULPTURE DETAILS ===");
        displayBasicInfo();
        System.out.println("Material: " + material);
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Lighting: " + lightingRequirement);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("=========================\n");
    }
    
    // Sculpture-specific methods
    public String getMaterial() { return material; }
    public String getDimensions() { return dimensions; }
    public String getLightingRequirement() { return lightingRequirement; }
    public double getWeight() { return weight; }
    
    public void adjustLighting(String newLighting) {
        this.lightingRequirement = newLighting;
        System.out.println("Lighting adjusted to: " + newLighting + " for sculpture: " + title);
    }
}

class DigitalArt extends Artwork {
    private String resolution;
    private String fileFormat;
    private boolean interactive;
    private String displayTechnology;
    
    public DigitalArt(String title, String artist, double price, String dateCreated,
                     String resolution, String fileFormat, boolean interactive, String displayTechnology) {
        super(title, artist, price, dateCreated);
        this.resolution = resolution;
        this.fileFormat = fileFormat;
        this.interactive = interactive;
        this.displayTechnology = displayTechnology;
    }
    
    @Override
    public void displayArtworkDetails() {
        System.out.println("=== DIGITAL ART DETAILS ===");
        displayBasicInfo();
        System.out.println("Resolution: " + resolution);
        System.out.println("File Format: " + fileFormat);
        System.out.println("Interactive: " + (interactive ? "Yes" : "No"));
        System.out.println("Display Technology: " + displayTechnology);
        System.out.println("===========================\n");
    }
    
    // Digital art-specific methods
    public String getResolution() { return resolution; }
    public String getFileFormat() { return fileFormat; }
    public boolean isInteractive() { return interactive; }
    public String getDisplayTechnology() { return displayTechnology; }
    
    public void enableInteractivity() {
        this.interactive = true;
        System.out.println("Interactive features enabled for: " + title);
    }
}

class Photography extends Artwork {
    private String cameraSettings;
    private String editingDetails;
    private String printSpecifications;
    private boolean limitedEdition;
    
    public Photography(String title, String artist, double price, String dateCreated,
                      String cameraSettings, String editingDetails, String printSpecifications, boolean limitedEdition) {
        super(title, artist, price, dateCreated);
        this.cameraSettings = cameraSettings;
        this.editingDetails = editingDetails;
        this.printSpecifications = printSpecifications;
        this.limitedEdition = limitedEdition;
    }
    
    @Override
    public void displayArtworkDetails() {
        System.out.println("=== PHOTOGRAPHY DETAILS ===");
        displayBasicInfo();
        System.out.println("Camera Settings: " + cameraSettings);
        System.out.println("Editing: " + editingDetails);
        System.out.println("Print Specs: " + printSpecifications);
        System.out.println("Limited Edition: " + (limitedEdition ? "Yes" : "No"));
        System.out.println("===========================\n");
    }
    
    // Photography-specific methods
    public String getCameraSettings() { return cameraSettings; }
    public String getEditingDetails() { return editingDetails; }
    public String getPrintSpecifications() { return printSpecifications; }
    public boolean isLimitedEdition() { return limitedEdition; }
    
    public void createNewPrint(String newSpecs) {
        this.printSpecifications = newSpecs;
        System.out.println("New print created with specs: " + newSpecs + " for: " + title);
    }
}

// Gallery Curator System demonstrating Downcasting
class GalleryCurator {
    
    public void planExhibition(Artwork[] artworks) {
        System.out.println("=== EXHIBITION PLANNING ===");
        System.out.println("Curator accessing specific artwork features for exhibition planning...\n");
        
        for (Artwork artwork : artworks) {
            System.out.println("Planning for: " + artwork.getTitle());
            
            // Downcasting to access specific features
            if (artwork instanceof Painting) {
                Painting painting = (Painting) artwork; // Downcasting
                System.out.println("Painting requirements:");
                System.out.println("- Frame: " + painting.getFrameType());
                System.out.println("- Canvas Size: " + painting.getCanvasSize());
                System.out.println("- Wall space needed for: " + painting.getCanvasSize());
                
                // Access painting-specific method
                if (painting.getFrameType().equals("Basic")) {
                    painting.changeFrame("Premium Gold");
                }
                
            } else if (artwork instanceof Sculpture) {
                Sculpture sculpture = (Sculpture) artwork; // Downcasting
                System.out.println("Sculpture requirements:");
                System.out.println("- Weight: " + sculpture.getWeight() + " kg");
                System.out.println("- Dimensions: " + sculpture.getDimensions());
                System.out.println("- Lighting: " + sculpture.getLightingRequirement());
                
                // Access sculpture-specific method
                if (sculpture.getWeight() > 100) {
                    System.out.println("Heavy sculpture - needs reinforced platform");
                }
                sculpture.adjustLighting("Dramatic Spotlight");
                
            } else if (artwork instanceof DigitalArt) {
                DigitalArt digitalArt = (DigitalArt) artwork; // Downcasting
                System.out.println("Digital art requirements:");
                System.out.println("- Resolution: " + digitalArt.getResolution());
                System.out.println("- Display Tech: " + digitalArt.getDisplayTechnology());
                System.out.println("- Interactive: " + digitalArt.isInteractive());
                
                // Access digital art-specific method
                if (!digitalArt.isInteractive()) {
                    digitalArt.enableInteractivity();
                }
                
            } else if (artwork instanceof Photography) {
                Photography photo = (Photography) artwork; // Downcasting
                System.out.println("Photography requirements:");
                System.out.println("- Print Specs: " + photo.getPrintSpecifications());
                System.out.println("- Limited Edition: " + photo.isLimitedEdition());
                System.out.println("- Camera Settings: " + photo.getCameraSettings());
                
                // Access photography-specific method
                if (photo.getPrintSpecifications().contains("Small")) {
                    photo.createNewPrint("Large Format 24x36 inch");
                }
            }
            
            artwork.setOnDisplay(true);
            System.out.println("✓ " + artwork.getTitle() + " prepared for exhibition\n");
        }
        
        System.out.println("Exhibition planning completed!");
        System.out.println("===========================\n");
    }
    
    public void generateCatalog(Artwork[] artworks) {
        System.out.println("=== EXHIBITION CATALOG ===");
        for (Artwork artwork : artworks) {
            artwork.displayArtworkDetails();
        }
        System.out.println("==========================\n");
    }
}

public class DigitalArtGalleryDemo {
    public static void main(String[] args) {
        // Create different types of artworks
        Artwork[] galleryCollection = {
            new Painting("Starry Night Reimagined", "Vincent Van Gogh II", 50000, "2024-01-15",
                        "Impressionist", "Blue-Yellow", "Basic", "16x20 inches"),
            
            new Sculpture("Modern Abstract", "Henry Moore Jr", 75000, "2024-02-20",
                         "Bronze", "3x2x4 feet", "Ambient", 150.5),
            
            new DigitalArt("Cyber Dreams", "AI Artist", 25000, "2024-03-10",
                          "4K", "MP4", false, "LED Wall"),
            
            new Photography("Urban Landscape", "Annie Leibovitz II", 15000, "2024-01-30",
                           "Canon 5D, f/2.8, 1/250s", "Adobe Lightroom", "Small Format", true)
        };
        
        // Create curator and demonstrate downcasting
        GalleryCurator curator = new GalleryCurator();
        
        // Plan exhibition using downcasting to access specific features
        curator.planExhibition(galleryCollection);
        
        // Generate catalog showing all artwork details
        curator.generateCatalog(galleryCollection);
    }
}
