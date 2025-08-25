package factory_method.RPG;

import java.util.Random;


public class CityMap extends Map {

    private Random random = new Random();

    @Override
    public Tile createTile() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0:
                return new RoadTile();
            case 1:
                return new BuildingTile();
            case 2:
                return new ForestTile();
            default:
                return new BuildingTile();
        }
    }
}