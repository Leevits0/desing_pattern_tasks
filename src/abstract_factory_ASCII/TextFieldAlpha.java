package abstract_factory_ASCII;

// TextFieldA.java
public class TextFieldAlpha extends TextField {
    public TextFieldAlpha(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("=^.^= < " + text + " >");
    }
}
