package state.characterlevelingsystem;

import java.util.List;
import java.util.Collections;

public class MasterLevel implements State {
    @Override public String name() { return "Master"; }
    @Override public int maxHealth(Character character) { return 160; }

    @Override public void onEnter(Character character) {
        System.out.println("🌟 You have attained mastery. All goals complete!");
    }

    @Override public void train(Character character)    { System.out.println("You are a Master—no further training needed."); }
    @Override public void meditate(Character character) { System.out.println("You are a Master—at peace already."); }
    @Override public void fight(Character character)    { System.out.println("You are a Master—no fights remain."); }

    @Override public List<String> availableActions(Character c) {
        return Collections.emptyList(); // game ends
    }
}
