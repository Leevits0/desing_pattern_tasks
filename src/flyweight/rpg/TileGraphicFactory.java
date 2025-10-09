// flyweight.rpg.TileGraphicFactory
package flyweight.rpg;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class TileGraphicFactory {
    private static final Map<String, TileGraphic> CACHE = new HashMap<>();

    public static TileGraphic get(String tileType, int tileSize) {
        String key = tileType.toLowerCase();
        return CACHE.computeIfAbsent(key, k -> {
            Image img = loadFromClasspath("/images/" + k + ".png");
            if (img == null) img = loadFromClasspath("/images/" + k + ".jpg");
            // if someone kept the dotted package style:
            if (img == null) img = loadFromClasspath("/resources/images/" + k + ".png");
            if (img == null) img = loadFromClasspath("/resources/images/" + k + ".jpg");
            if (img == null) img = solidColorImage(colorFor(k), tileSize); // fallback
            return new TileGraphic(img);
        });
    }

    public static int uniqueGraphicCount() { return CACHE.size(); }

    private static Image loadFromClasspath(String path) {
        URL url = TileGraphicFactory.class.getResource(path);
        return (url != null) ? new Image(url.toExternalForm()) : null;
    }

    private static Image solidColorImage(Color c, int size) {
        Canvas canvas = new Canvas(size, size);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(c);
        g.fillRect(0, 0, size, size);
        g.setStroke(Color.BLACK);
        g.strokeRect(0.5, 0.5, size - 1, size - 1);
        WritableImage img = new WritableImage(size, size);
        canvas.snapshot(null, img);
        return img;
    }

    private static Color colorFor(String key) {
        return switch (key) {
            case "swamp"    -> Color.DARKOLIVEGREEN;
            case "forest"   -> Color.FORESTGREEN;
            case "water"    -> Color.ROYALBLUE;
            case "road"     -> Color.SANDYBROWN;
            case "building" -> Color.SILVER;
            default         -> Color.LIGHTGRAY;
        };
    }
}
