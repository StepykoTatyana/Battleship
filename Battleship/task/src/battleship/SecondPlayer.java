package battleship;


import java.util.ArrayList;
import java.util.List;

public class SecondPlayer {
    static String[][] arraySecond = new String[SuperClass.rowCount][SuperClass.seatCountInRow];
    static List<List<String>> arrayWithCoordinateSecond = new ArrayList();
    static List<List<String>> arrayWithCoordinateSinkShipsSecond = new ArrayList();
    static String[][] arrayForBattleFirst = new String[SuperClass.rowCount][SuperClass.seatCountInRow];
}
