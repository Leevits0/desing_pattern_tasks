package state.characterlevelingsystem;

import java.util.List;
import java.util.Scanner;

public class Game {
    public void run() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter character name: ");
        String name = in.nextLine();
        Character ch = new Character(name);

        boolean running = true;
        while (running) {
            ch.showStatus();

            List<String> actions = ch.availableActions();
            if (actions.isEmpty()) {
                System.out.println("Game over. Congrats on reaching MASTER!");
                break;
            }

            System.out.println("--- ACTIONS -----------------------------");
            for (int i = 0; i < actions.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, actions.get(i));
            }
            System.out.println("0) Quit");
            System.out.println("-----------------------------------------");

            System.out.print("Choose an action: ");
            String cmd = in.nextLine().trim().toLowerCase();

            switch (cmd) {
                case "1": if (actions.contains("train")) ch.train(); break;
                case "2": if (actions.contains("meditate")) ch.meditate(); break;
                case "3": if (actions.contains("fight")) ch.fight(); break;
                case "0": running = false; break;
                default:  System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Goodbye!");
    }
}
