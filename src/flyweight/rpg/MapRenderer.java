package flyweight.rpg;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import factorymethod.rpg.Map;

public final class MapRenderer {

    private final int tileSize;

    public MapRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void render(Map map, Canvas canvas) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        int rows = map.getRows();
        int cols = map.getCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                var tile = map.getTile(r, c);
                var fly = TileGraphicFactory.get(tile.getTileType(), tileSize);
                g.drawImage(fly.getImage(), c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
    }
}
