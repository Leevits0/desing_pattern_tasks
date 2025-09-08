package abstractfactory.ascii;

// ButtonB.java
public class ButtonBeta extends Button {
    public ButtonBeta(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("U•ᴥ•U [ " + text + " ] U•ᴥ•U");
    }
}
