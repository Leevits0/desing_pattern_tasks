package abstractfactory.ascii;

// CheckboxB.java
public class CheckboxBeta extends Checkbox {
    public CheckboxBeta(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("U•ᴥ•U ( ) " + text);
    }
}

