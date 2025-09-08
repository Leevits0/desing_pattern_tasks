package state.characterlevelingsystem;

import java.util.List;

public class Character {
    private final String name;
    private int level;          // 1..4 (Novice..Master)
    private int experience;     // XP
    private int health;         // HP
    private State state;

    // XP thresholds to reach the next level
    private static final int XP_TO_INTERMEDIATE = 50;
    private static final int XP_TO_EXPERT      = 100;
    private static final int XP_TO_MASTER      = 200;

    public Character(String name) {
        this.name = (name == null || name.isBlank()) ? "Hero" : name.trim();
        this.level = 1;
        this.experience = 0;
        setState(new NoviceLevel());
        this.health = maxHealth(); // start with full health
    }

    // Actions
    public void train()    { state.train(this);    checkLevelUp(); }
    public void meditate() { state.meditate(this); checkLevelUp(); }
    public void fight()    { state.fight(this);    checkLevelUp(); }

    // Leveling & transitions
    private void checkLevelUp() {
        if (level == 1 && experience >= XP_TO_INTERMEDIATE) {
            level = 2; setState(new IntermediateLevel());
            System.out.println(">> Level Up! You are now INTERMEDIATE.");
            softRefillHP();
        } else if (level == 2 && experience >= XP_TO_EXPERT) {
            level = 3; setState(new ExpertLevel());
            System.out.println(">> Level Up! You are now EXPERT.");
            softRefillHP();
        } else if (level == 3 && experience >= XP_TO_MASTER) {
            level = 4; setState(new MasterLevel());
            System.out.println(">> Level Up! You reached MASTER. Game complete!");
            softRefillHP();
        }
    }

    private void softRefillHP() {
        setHealth(Math.min(maxHealth(), getHealth() + 10));
    }

    void setState(State s) {
        this.state = s;
        s.onEnter(this);
        // update health if maxHealth changed
        if (health > s.maxHealth(this)) health = s.maxHealth(this);
    }

    // Utility used by states
    public void addXP(int xp)    { experience = Math.max(0, experience + xp); }
    public void addHP(int hp)    { setHealth(health + hp); }
    public void damageHP(int hp) { setHealth(health - hp); }
    public int  maxHealth()      { return state.maxHealth(this); }

    // Getters/Setters
    public String getName() { return name; }
    public int getLevel()   { return level; }
    public int getXP()      { return experience; }
    public int getHealth()  { return health; }
    public void setHealth(int h) { health = Math.max(0, Math.min(h, maxHealth())); }
    public String stateName()    { return state.name(); }
    public List<String> availableActions() { return state.availableActions(this); }

    // status helper menu
    public void showStatus() {
        System.out.println("\n--- STATUS --------------------------------");
        System.out.printf("Name    : %s%n", name);
        System.out.printf("Level   : %d (%s)%n", level, stateName());
        System.out.printf("XP      : %d%n", experience);
        System.out.printf("HP      : %d/%d%n", health, maxHealth());
        System.out.println("-------------------------------------------\n");
    }
}
