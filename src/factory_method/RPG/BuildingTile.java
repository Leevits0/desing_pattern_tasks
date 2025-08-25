package factory_method.RPG;

public class BuildingTile implements Tile {
    public char getCharacter() { return 'B'; }
    public String getTileType()    { return "building"; }
    public void action() { System.out.println("You are wading through a building?!?!."); }
}
