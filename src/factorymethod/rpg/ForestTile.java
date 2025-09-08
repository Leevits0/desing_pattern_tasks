package factorymethod.rpg;

public class ForestTile implements Tile {
    public char getCharacter() { return 'F'; }
    public String getTileType()    { return "forest"; }
    public void action() { System.out.println("You are wading through the forest."); }
}
