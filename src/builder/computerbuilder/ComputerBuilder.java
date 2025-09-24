package builder.computerbuilder;

public interface ComputerBuilder {
    void buildProcessor();
    void buildRAM();
    void buildHardDrive();
    void buildGraphicsCard();
    void buildOperatingSystem();

    // customization hook
    default void addAccessory(String accessory) {}

    Computer getComputer();
}
