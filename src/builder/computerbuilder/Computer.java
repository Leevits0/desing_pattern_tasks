package builder.computerbuilder;

import java.util.ArrayList;
import java.util.List;

public final class Computer {
    private String processor;
    private int ramGb;
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;

    // extras (for the “additional challenges”)
    private final List<String> accessories = new ArrayList<>();

    // package-private setters used by builders
    void setProcessor(String processor) { this.processor = processor; }
    void setRamGb(int ramGb) { this.ramGb = ramGb; }
    void setHardDrive(String hardDrive) { this.hardDrive = hardDrive; }
    void setGraphicsCard(String graphicsCard) { this.graphicsCard = graphicsCard; }
    void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }
    void addAccessory(String accessory) { this.accessories.add(accessory); }

    @Override
    public String toString() {
        return """
               Computer
               - CPU: %s
               - RAM: %d GB
               - Storage: %s
               - GPU: %s
               - OS: %s
               - Accessories: %s
               """.formatted(processor, ramGb, hardDrive, graphicsCard, operatingSystem,
                accessories.isEmpty() ? "None" : accessories);
    }
}
