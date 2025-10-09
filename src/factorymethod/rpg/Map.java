package factorymethod.rpg;

import java.util.Scanner;

public abstract class Map {

    protected final int ROWS = 5;
    protected final int COLS = 5;
    protected Tile[][] tiles = new Tile[ROWS][COLS];


    public void display() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                tiles[i][j] = createTile();
            }
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(tiles[i][j].getCharacter() + " ");
            }
            System.out.println();
        }
    }


    public void play() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nExploring the map:");
        outerLoop:
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Tile tile = tiles[i][j];
                System.out.println("You are in a " + tile.getTileType() + " tile [" + tile.getCharacter() + "]");
                tile.action();
                System.out.println("* Press enter to move to the next room ('q' to quit). *");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("q")) {
                    break outerLoop;
                }
            }
        }
    }
    public abstract Tile createTile();

    public int getRows() { return ROWS; }

    public int getCols() { return COLS; }

    public Tile getTile(int row, int col) { return tiles[row][col]; }
}
