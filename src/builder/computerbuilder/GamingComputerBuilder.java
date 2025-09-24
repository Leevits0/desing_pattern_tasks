package builder.computerbuilder;


public final class GamingComputerBuilder implements ComputerBuilder {
    private final Computer pc = new Computer();

    @Override public void buildProcessor()      { pc.setProcessor("AMD Ryzen 7 7800X3D"); }
    @Override public void buildRAM()            { pc.setRamGb(32); }
    @Override public void buildHardDrive()      { pc.setHardDrive("1 TB NVMe SSD"); }
    @Override public void buildGraphicsCard()   { pc.setGraphicsCard("NVIDIA GeForce RTX 4080"); }
    @Override public void buildOperatingSystem(){ pc.setOperatingSystem("Windows 11 Pro"); }

    @Override public void addAccessory(String accessory) { pc.addAccessory(accessory); }

    @Override public Computer getComputer() { return pc; }
}
