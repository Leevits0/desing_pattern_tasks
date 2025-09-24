package builder.computerbuilder;

public final class OfficeComputerBuilder implements ComputerBuilder {
    private final Computer pc = new Computer();

    @Override public void buildProcessor()      { pc.setProcessor("Intel Core i5-13400"); }
    @Override public void buildRAM()            { pc.setRamGb(16); }
    @Override public void buildHardDrive()      { pc.setHardDrive("512 GB SSD"); }
    @Override public void buildGraphicsCard()   { pc.setGraphicsCard("Integrated Graphics"); }
    @Override public void buildOperatingSystem(){ pc.setOperatingSystem("Windows 11 Home"); }

    @Override public void addAccessory(String accessory) { pc.addAccessory(accessory); }

    @Override public Computer getComputer() { return pc; }
}

