package composite;

public final class Employee implements OrgUnit {
    private final String name;
    private final double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override public String getName() { return name; }

    @Override public double totalSalary() { return salary; }

    // self-closing XML tag
    @Override public String toXml(int indent) {
        String pad = " ".repeat(indent);
        return pad + "<employee name=\"" + escape(name) + "\" salary=\"" + salary + "\"/>\n";
    }

    // --- small helper for XML ---
    private static String escape(String s) {
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&apos;");
    }
}
