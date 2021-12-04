package battleship;


import java.util.LinkedHashMap;

public abstract class SuperClass {
    final static int seatCountInRow = 10;
    final static int rowCount = 10;
    static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    static char[] chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static LinkedHashMap<String, Integer> ships = new LinkedHashMap<>();
    static LinkedHashMap<Character, Integer> lettersMap = new LinkedHashMap<>();

    public abstract void feelArea(String[][] arrayForFeel, int numberPlayer);


    public abstract String[][] feel(String[] coordinateArray, String typeShip, int sizeShip, String[][] arrayForFeel, int numberPlayer);

    public abstract void battle(String[][] arrayPlayer, int i);

    public static void feelDict() {
        ships.put("Aircraft Carrier", 5);
        ships.put("Battleship", 4);
        ships.put("Submarine", 3);
        ships.put("Cruiser", 3);
        ships.put("Destroyer", 2);
        lettersMap.put('A', 1);
        lettersMap.put('B', 2);
        lettersMap.put('C', 3);
        lettersMap.put('D', 4);
        lettersMap.put('E', 5);
        lettersMap.put('F', 6);
        lettersMap.put('G', 7);
        lettersMap.put('H', 8);
        lettersMap.put('I', 9);
        lettersMap.put('J', 10);
    }

    public static void createArray(String[][] array) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < seatCountInRow; j++) {
                array[i][j] = "~";
            }
        }
    }

    public static void printArea(String[][] array) {
        System.out.print("  ");
        for (int i = 1; i <= seatCountInRow; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rowCount; i++) {
            System.out.print(letters[i] + " ");

            for (int j = 0; j < seatCountInRow; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}
