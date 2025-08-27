package composite;

public class Main {
    public static void main(String[] args) {
        Organization org = new Organization();

        Department engineering = new Department("Engineering");
        Department platform = new Department("Platform");

        Employee alice = new Employee("Alice", 90_000);
        Employee bob   = new Employee("Bob",   95_000);
        Employee chris = new Employee("Chris", 120_000);

        // Build tree
        engineering.add(alice);
        engineering.add(platform);
        platform.add(bob);
        platform.add(chris);

        // Attach to org
        org.add(engineering);

        // One-call salary + XML
        org.printTotalSalary();  // expect 305000.0
        org.printXml();
    }
}
