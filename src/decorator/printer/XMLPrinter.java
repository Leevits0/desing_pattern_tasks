package decorator.printer;

public class XMLPrinter extends AbstractPrinterDecorator {

    public XMLPrinter(Printer wrappee) {
        super(wrappee);
    }

    @Override
    public void print(String message) {
        String xml = "<message>" + escapeXml(message) + "</message>";
        wrappee.print(xml);
    }

    private static String escapeXml(String s) {
        if (s == null) return "";
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (char c : s.toCharArray()) {
            switch (c) {
                case '&': sb.append("&amp;"); break;
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '"': sb.append("&quot;"); break;
                case '\'': sb.append("&apos;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }
}
