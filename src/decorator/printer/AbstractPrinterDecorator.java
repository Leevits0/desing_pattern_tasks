package decorator.printer;


public abstract class AbstractPrinterDecorator implements Printer {
    protected final Printer wrappee;

    protected AbstractPrinterDecorator(Printer wrappee) {
        if (wrappee == null) throw new IllegalArgumentException("wrappee cannot be null");
        this.wrappee = wrappee;
    }
}
