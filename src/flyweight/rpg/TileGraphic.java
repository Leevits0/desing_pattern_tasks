package flyweight.rpg;

import javafx.scene.image.Image;

public final class TileGraphic {
    private final Image image;

    public TileGraphic(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
