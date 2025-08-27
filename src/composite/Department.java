package composite;

import java.util.ArrayList;
import java.util.List;

public final class Department implements OrgUnit {
    private final String name;
    private final List<OrgUnit> children = new ArrayList<>();

    public Department(String name) { this.name = name; }

    @Override public String getName() { return name; }

    @Override
    public double totalSalary() {
        double sum = 0.0;
        for (OrgUnit u : children) sum += u.totalSalary();
        return sum;
    }

    @Override
    public void add(OrgUnit child) { children.add(child); }

    @Override
    public void remove(OrgUnit child) { children.remove(child); }

    @Override
    public String toXml(int indent) {
        String pad = " ".repeat(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("<department name=\"").append(escape(name)).append("\">\n");
        for (OrgUnit u : children) {
            sb.append(u.toXml(indent + 2));
        }
        sb.append(pad).append("</department>\n");
        return sb.toString();
    }

    private static String escape(String s) {
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&apos;");
    }
}
