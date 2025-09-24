package builder.computerbuilder;

public final class ComputerDirector {
    private ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }

    // The recipe for assembling a full machine
    public Computer constructComputer() {
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        return builder.getComputer();
    }

    // Example of a different recipe (optional)
    public Computer constructWithAccessories(String... accessories) {
        Computer pc = constructComputer();
        for (String a : accessories) builder.addAccessory(a);
        return pc;
    }
}
