package factory_method.RPG;

public class SwampTile implements Tile {
    public char getCharacter() { return 'S'; }
    public String getTileType()    { return "swamp"; }
    public void action() { System.out.println("You are wading through the swamp."); }
}


