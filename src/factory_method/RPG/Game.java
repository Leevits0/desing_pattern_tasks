package factory_method.RPG;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map map;

        System.out.print("Enter map type (city or wilderness): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("city")) {
            map = new CityMap();
        } else if (type.equalsIgnoreCase("wilderness")) {
            map = new WildernessMap();
        } else {
            System.out.println("Unknown map type.");
            return;
        }
        map.display();
        map.play();
    }
}

