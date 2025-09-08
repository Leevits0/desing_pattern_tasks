package state.characterlevelingsystem;

import java.util.List;
import java.util.Arrays;

public class IntermediateLevel implements State {
    @Override public String name() { return "Intermediate"; }
    @Override public int maxHealth(Character character) { return 120; }

    @Override public void train(Character character) {
        character.addXP(15);
        System.out.println("You hone your skills. +15 XP.");
    }
    @Override public void meditate(Character character) {
        character.addHP(10);
        System.out.println("You meditate and recover. +10 HP.");
    }
    @Override public void fight(Character character) {
        System.out.println("Fighting is locked at Intermediate. Keep training!");
    }
    @Override public List<String> availableActions(Character character) {
        return Arrays.asList("train", "meditate");
    }
}
