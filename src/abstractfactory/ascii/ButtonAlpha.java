package abstractfactory.ascii;

// ButtonA.java
public class ButtonAlpha extends Button {
    public ButtonAlpha(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("=^.^= [ " + text + " ] =^.^=");
    }
}
