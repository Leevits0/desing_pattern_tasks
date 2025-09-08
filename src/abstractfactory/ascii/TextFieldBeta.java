package abstractfactory.ascii;

// TextFieldB.java
public class TextFieldBeta extends TextField {
    public TextFieldBeta(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("U•ᴥ•U < " + text + " >");
    }
}

