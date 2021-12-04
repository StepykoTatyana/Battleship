package battleship;

import java.util.*;

public class Main extends SuperClass {
    static int result = 0;

    public static void main(String[] args) {
        Main first = new Main();
        Main secondPlayer = new Main();
        Scanner newScanner = new Scanner(System.in);
        feelDict();
        createArray(FirstPlayer.arrayForBattleSecond);
        createArray(SecondPlayer.arrayForBattleFirst);
        for (int i = 1; i != 3; i++) {
            System.out.printf("Player %d, place your ships on the game field\n\n", i);
            if (i == 1) {
                createArray(FirstPlayer.arrayFirst);
                printArea(FirstPlayer.arrayFirst);
                first.feelArea(FirstPlayer.arrayFirst, i);

            } else {
                createArray(SecondPlayer.arraySecond);
                printArea(SecondPlayer.arraySecond);
                secondPlayer.feelArea(SecondPlayer.arraySecond, i);

            }
            System.out.println();
            System.out.println("Press Enter and pass the move to another player");
            newScanner.nextLine();
        }
        while (result == 0) {
            for (int i = 1; i != 3; i++) {
                if (result == 1){
                    break;
                }
                if (i == 1) {
                    first.battle(FirstPlayer.arrayFirst, i);
                } else {
                    first.battle(SecondPlayer.arraySecond, i);
                }
                System.out.println("Press Enter and pass the move to another player");
                newScanner.nextLine();
            }
        }
    }

    @Override
    public void feelArea(String[][] arrayForFeel, int numberPlayer) {
        Scanner scanner = new Scanner(System.in);
        for (Map.Entry<String, Integer> entry : ships.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            String[][] newArray = null;
            String[] coordinate;
            System.out.println();
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", key, value);
            while (newArray == null) {
                try {
                    coordinate = scanner.nextLine().trim().split(" ");
                    newArray = feel(coordinate, key, value, arrayForFeel, numberPlayer);
                } catch (Exception e) {
                    System.out.printf("Enter the coordinates of the %s (%d cells):\n", key, value);
                }

            }
            arrayForFeel = newArray;
            System.out.println();
            printArea(arrayForFeel);
        }
    }

    @Override
    public String[][] feel(String[] coordinateArray, String typeShip, int sizeShip, String[][] arrayForFeel, int numberPlayer) {
        char xS = (char) Math.min((coordinateArray[0].replaceAll("[0-9]", "")).charAt(0), (coordinateArray[1].replaceAll("[0-9]", "")).charAt(0));
        char yS = (char) Math.max((coordinateArray[0].replaceAll("[0-9]", "")).charAt(0), (coordinateArray[1].replaceAll("[0-9]", "")).charAt(0));
        int wrong = 0;
        int x = Math.min(Integer.parseInt(coordinateArray[0].replaceAll("[A-Z]", "")), Integer.parseInt(coordinateArray[1].replaceAll("[A-Z]", "")));
        int y = Math.max(Integer.parseInt(coordinateArray[0].replaceAll("[A-Z]", "")), Integer.parseInt(coordinateArray[1].replaceAll("[A-Z]", "")));
        List<String> coo = new ArrayList<>();
        if (xS >= 'A' && yS <= 'J' && x >= 0 && y <= 10) {
            for (int k = x - 2; k < y; k++) {
                if (k < 0) {
                    k = 0;
                }
                if (lettersMap.get(xS) - 1 >= 0 && lettersMap.get(xS) - 1 < 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(xS) - 1][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
                if (lettersMap.get(xS) - 2 >= 0 && lettersMap.get(xS) - 2 < 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(xS) - 2][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
                if (lettersMap.get(xS) + 1 < 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(xS)][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
                if (lettersMap.get(yS) - 1 < 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(yS) - 1][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
                if (lettersMap.get(yS) < 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(yS)][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
                if (lettersMap.get(yS) != 10) {
                    if (Objects.equals(arrayForFeel[lettersMap.get(yS)][k], "O")) {
                        wrong = 1;
                        break;
                    }
                }
            }
            if (xS == yS) {
                if ((y - x) + 1 == sizeShip) {
                    for (int i = 0; i < rowCount; i++) {
                        if (xS == chars[i]) {
                            for (int j = 0; j < seatCountInRow; j++) {
                                if (wrong != 1) {
                                    if (j + 1 >= x && j + 1 <= y) {
                                        arrayForFeel[i][j] = "O";
                                    }
                                } else {
                                    System.out.println();
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    System.out.println();
                                    return null;
                                }
                            }
                        }
                    }
                    coo.add(xS + String.valueOf(x));
                    coo.add(yS + String.valueOf(y));
                    if (numberPlayer == 1) {
                        FirstPlayer.arrayWithCoordinateFirst.add(coo);
                    } else {
                        SecondPlayer.arrayWithCoordinateSecond.add(coo);
                    }
                    return arrayForFeel;
                } else {
                    System.out.println();
                    System.out.printf("Error! Wrong length of the %s! Try again:\n", typeShip);
                    System.out.println();
                    return null;
                }
            } else {
                if (x == y) {
                    if ((yS - xS) + 1 == sizeShip) {
                        for (int i = 0; i < seatCountInRow; i++) {
                            if (chars[i] >= xS && chars[i] <= yS) {
                                if (wrong != 1) {
                                    for (int j = 0; j < seatCountInRow; j++) {
                                        if (j == x) {
                                            arrayForFeel[i][j - 1] = "O";
                                        }
                                    }
                                } else {
                                    System.out.println();
                                    System.out.println("Error! You placed it too close to another one. Try again:");
                                    System.out.println();
                                    return null;
                                }
                            }
                        }
                        coo.add(xS + String.valueOf(x));
                        coo.add(yS + String.valueOf(y));
                        if (numberPlayer == 1) {
                            FirstPlayer.arrayWithCoordinateFirst.add(coo);
                        } else {
                            SecondPlayer.arrayWithCoordinateSecond.add(coo);
                        }
                        return arrayForFeel;
                    } else {
                        System.out.println();
                        System.out.printf("Error! Wrong length of the %s! Try again:\n", typeShip);
                        System.out.println();
                        return null;

                    }
                } else {
                    System.out.println();
                    System.out.println("Error! Wrong ship location! Try again:");
                    System.out.println();
                    return null;
                }
            }
        } else {
            System.out.println();
            System.out.println("Error! Wrong ship location! Try again: ");
            System.out.println();
            return null;
        }
    }

    @Override
    public void battle(String[][] arrayPlayer, int numberPlayer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        if (numberPlayer == 1) {
            printArea(FirstPlayer.arrayForBattleSecond);
        } else {
            printArea(SecondPlayer.arrayForBattleFirst);
        }

        System.out.println("---------------------");
        printArea(arrayPlayer);
        System.out.printf("\nPlayer %d, it's your turn:\n\n", numberPlayer);
        String choice = scanner.nextLine();
        System.out.println();
        if (numberPlayer == 2) {
            checkStep(choice, FirstPlayer.arrayFirst, FirstPlayer.arrayWithCoordinateFirst, FirstPlayer.arrayWithCoordinateSinkShipsFirst, numberPlayer);
        } else {
            checkStep(choice, SecondPlayer.arraySecond, SecondPlayer.arrayWithCoordinateSecond, SecondPlayer.arrayWithCoordinateSinkShipsSecond, numberPlayer);
        }
    }

    private void checkStep(String choice, String[][] arrayPlayer, List<List<String>> arrayWithCoordinate, List<List<String>> arrayWithCoordinateSinkShips, int numberPlayer) {
        char xS = (choice.replaceAll("[0-9]", "")).charAt(0);
        int x = Integer.parseInt(choice.replaceAll("[A-Z]", ""));
        if (xS >= 'A' && xS <= 'J' && x >= 0 && x <= 10) {
            if (Objects.equals(arrayPlayer[lettersMap.get(xS) - 1][x - 1], "O")) {
                arrayPlayer[lettersMap.get(xS) - 1][x - 1] = "X";
                if (numberPlayer == 1) {
                    FirstPlayer.arrayForBattleSecond[lettersMap.get(xS) - 1][x - 1] = "X";
                } else {
                    SecondPlayer.arrayForBattleFirst[lettersMap.get(xS) - 1][x - 1] = "X";
                }
                int oldSize = arrayWithCoordinateSinkShips.size();
                for (List<String> a : arrayWithCoordinate) {
                    if (!arrayWithCoordinateSinkShips.contains(a)) {
                        if (checkNew(a, arrayPlayer)) {
                            arrayWithCoordinateSinkShips.add(a);
                        }
                    }
                }
                if (oldSize != arrayWithCoordinateSinkShips.size()) {
                    if (arrayWithCoordinateSinkShips.size() == arrayWithCoordinate.size()) {
                        System.out.println("You sank the last ship. You won. Congratulations!\n\n");
                        result = 1;
                    } else {
                        System.out.println("You sank a ship!\n");
                    }

                } else {
                    System.out.println("You hit a ship!");
                }
            } else {
                if (!Objects.equals(arrayPlayer[lettersMap.get(xS) - 1][x - 1], "X")) {
                    arrayPlayer[lettersMap.get(xS) - 1][x - 1] = "M";

                    if (numberPlayer == 1) {
                        FirstPlayer.arrayForBattleSecond[lettersMap.get(xS) - 1][x - 1] = "M";
                    } else {
                        SecondPlayer.arrayForBattleFirst[lettersMap.get(xS) - 1][x - 1] = "M";
                    }
                }
                System.out.println("You missed!");
            }
        } else {
            System.out.println("Error! You entered the wrong coordinates!\n");
        }
    }

    private boolean checkNew(List<String> str, String[][] arrayPlayer) {
        char xS = (str.get(0).replaceAll("[0-9]", "")).charAt(0);
        char yS = (str.get(1).replaceAll("[0-9]", "")).charAt(0);
        int x = Integer.parseInt(str.get(0).replaceAll("[A-Z]", ""));
        int y = Integer.parseInt(str.get(1).replaceAll("[A-Z]", ""));
        int k = 0;
        int sizeShip;
        if (xS == yS) {
            sizeShip = y - x + 1;
            for (int i = 0; i < rowCount; i++) {
                if (xS == chars[i]) {
                    for (int j = x - 1; j < y; j++) {
                        if (Objects.equals(arrayPlayer[i][j], "X")) {
                            k++;
                        }
                    }
                }
            }
        } else {
            sizeShip = lettersMap.get(yS) - lettersMap.get(xS) + 1;
            for (int i = lettersMap.get(xS) - 1; i < lettersMap.get(yS); i++) {
                for (int j = 0; j < seatCountInRow; j++) {
                    if (j == x - 1) {
                        if (Objects.equals(arrayPlayer[i][j], "X")) {
                            k++;
                        }
                    }
                }
            }
        }
        return k == sizeShip;
    }
}
