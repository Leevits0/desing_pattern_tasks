package abstractfactory.ascii;

// TextFieldA.java
public class TextFieldAlpha extends TextField {
    public TextFieldAlpha(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("=^.^= < " + text + " >");
    }
}
