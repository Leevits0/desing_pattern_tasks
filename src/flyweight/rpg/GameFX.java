package flyweight.rpg;

import factorymethod.rpg.Map;
import factorymethod.rpg.WildernessMap;
import factorymethod.rpg.CityMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GameFX extends Application {

    private static final int TILE_SIZE = 96; // adjust to taste

    @Override
    public void start(Stage stage) {

        // map implementation:
        Map map = new WildernessMap();
        map.display();

        Canvas canvas = new Canvas(map.getCols() * TILE_SIZE, map.getRows() * TILE_SIZE);
        MapRenderer renderer = new MapRenderer(TILE_SIZE);
        renderer.render(map, canvas);

        StackPane root = new StackPane(canvas);
        stage.setScene(new Scene(root));
        stage.setTitle("RPG Map – Flyweight Rendering");
        stage.show();

        System.out.println("Flyweights created: " + TileGraphicFactory.uniqueGraphicCount());
    }

    public static void main(String[] args) {
        launch();
    }
}
