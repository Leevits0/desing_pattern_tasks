package decorator.printer;

public class Main {
    public static void main(String[] args) {

        // Basic printer
        Printer printer = new BasicPrinter();
        printer.print("Hello World!");

        // Decorated: XML then Encrypted printer
        Printer printer2 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()), "myKey42");
        printer2.print("Hello World!");

    }
}
