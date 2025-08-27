package composite;

import java.util.ArrayList;
import java.util.List;

public final class Organization {
    private final List<OrgUnit> roots = new ArrayList<>();

    // single-call convenience
    public void add(OrgUnit unit) { roots.add(unit); }
    public void remove(OrgUnit unit) { roots.remove(unit); }

    public double totalSalary() {
        double sum = 0.0;
        for (OrgUnit u : roots) sum += u.totalSalary();
        return sum;
    }

    public String toXml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<organization>\n");
        for (OrgUnit u : roots) {
            sb.append(u.toXml(2));
        }
        sb.append("</organization>\n");
        return sb.toString();
    }

    // helper to print with one call
    public void printTotalSalary() {
        System.out.println(totalSalary());
    }

    public void printXml() {
        System.out.println(toXml());
    }
}
