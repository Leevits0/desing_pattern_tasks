package factorymethod.rpg;

public class WaterTile implements Tile {
    public char getCharacter() { return 'W'; }
    public String getTileType()    { return "water"; }
    public void action() { System.out.println("Wateeeeerrrr splaaash"); }
}
