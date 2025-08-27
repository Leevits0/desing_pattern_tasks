package abstract_factory_ASCII;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Choose UI style (A/B): ");
        String choice = sc.nextLine().trim();

        UIFactory factory = choice.equalsIgnoreCase("B") ? new BetaFactory() : new AlphaFactory();

        Button btn = factory.createButton("Click Me");
        TextField tf = factory.createTextField("Enter name");
        Checkbox cb = factory.createCheckbox("Accept terms");

        System.out.println("\n--- Displaying UI ---");
        btn.display();
        tf.display();
        cb.display();

        // test for setText()
        btn.setText("Submit");
        tf.setText("New Value");
        cb.setText("Subscribe to newsletter");

        System.out.println("\n--- After setText() ---");
        btn.display();
        tf.display();
        cb.display();
    }
}
