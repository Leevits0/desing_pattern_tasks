package abstract_factory_ASCII;

// AFactory.java
public class AlphaFactory extends UIFactory {
    @Override
    public Button createButton(String text) {
        return new ButtonAlpha(text);
    }

    @Override
    public TextField createTextField(String text) {
        return new TextFieldAlpha(text);
    }

    @Override
    public Checkbox createCheckbox(String text) {
        return new CheckboxAlpha(text);
    }
}
