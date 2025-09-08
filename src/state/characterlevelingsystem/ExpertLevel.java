package state.characterlevelingsystem;

import java.util.List;
import java.util.Arrays;

public class ExpertLevel implements State {
    @Override public String name() { return "Expert"; }
    @Override public int maxHealth(Character character) { return 140; }

    @Override public void train(Character character) {
        character.addXP(20);
        System.out.println("Advanced drills completed. +20 XP.");
    }
    @Override public void meditate(Character character) {
        character.addHP(12);
        System.out.println("Deep meditation restores you. +12 HP.");
    }
    @Override public void fight(Character character) {
        character.damageHP(20);
        character.addXP(25);
        System.out.println("You fought a fierce battle! -20 HP, +25 XP.");
        if (character.getHealth() <= 0) {
            System.out.println("You were knocked out! Rest to continue...");
            character.setHealth(1); // prevent death, set to 1 HP
        }
    }
    @Override public List<String> availableActions(Character character) {
        return Arrays.asList("train", "meditate", "fight");
    }
}
