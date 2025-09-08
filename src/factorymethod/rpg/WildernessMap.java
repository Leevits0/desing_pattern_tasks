package factorymethod.rpg;

import java.util.Random;


public class WildernessMap extends Map {

    private Random random = new Random();

    @Override
    public Tile createTile() {
        int choice = random.nextInt(3); // For 3 tile types
        switch (choice) {
            case 0:
                return new SwampTile();
            case 1:
                return new ForestTile();
            case 2:
                return new WaterTile();
            default:
                return new SwampTile(); // Fallback
        }
    }
}