package abstract_factory_ASCII;

// CheckboxA.java
public class CheckboxAlpha extends Checkbox {
    public CheckboxAlpha(String text) { super(text); }

    @Override
    public void display() {
        System.out.println("=^.^= [ ] " + text);
    }
}

