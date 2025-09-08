package abstractfactory.ascii;

// BFactory.java
public class BetaFactory extends UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonBeta(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldBeta(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxBeta(text);
    }
}
