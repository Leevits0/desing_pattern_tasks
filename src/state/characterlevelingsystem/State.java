package state.characterlevelingsystem;

import java.util.List;

public interface State {
    String name();

    // lifecycle hook when entering a state
    default void onEnter(Character character) {}

    // actions
    void train(Character character);
    void meditate(Character character);
    void fight(Character character);

    // for the UI
    List<String> availableActions(Character character);

    // per-level max health
    int maxHealth(Character character);
}
