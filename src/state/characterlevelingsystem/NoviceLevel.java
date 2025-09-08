package state.characterlevelingsystem;

import java.util.List;
import java.util.Arrays;

public class NoviceLevel implements State {
    @Override public String name() { return "Novice"; }
    @Override public int maxHealth(Character character) { return 100; }

    @Override public void train(Character character) {
        character.addXP(10);
        System.out.println("You practice basics. +10 XP.");
    }
    @Override public void meditate(Character character) {
        System.out.println("Meditation is locked at Novice. Keep training!");
    }
    @Override public void fight(Character character) {
        System.out.println("Fighting is locked at Novice. Keep training!");
    }
    @Override public List<String> availableActions(Character c) {
        return Arrays.asList("train");
    }
}
