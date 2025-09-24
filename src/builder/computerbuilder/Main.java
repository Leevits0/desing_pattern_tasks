package builder.computerbuilder;

public class Main {
    public static void main(String[] args) {
        ComputerDirector director = new ComputerDirector(new GamingComputerBuilder());

        // Build a gaming PC
        Computer gaming = director.constructWithAccessories("Mechanical Keyboard", "RGB Mouse");
        System.out.println(gaming);

        // Switch builder and build an office PC
        director.setBuilder(new OfficeComputerBuilder());
        Computer office = director.constructWithAccessories("Ergonomic Keyboard", "Docking Station");
        System.out.println(office);
    }
}
