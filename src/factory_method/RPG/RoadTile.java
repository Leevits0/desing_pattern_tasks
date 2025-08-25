package factory_method.RPG;

public class RoadTile implements Tile {
    public char getCharacter() { return 'R'; }
    public String getTileType()    { return "road"; }
    public void action() { System.out.println("Asphalt in your lungs"); }
}
