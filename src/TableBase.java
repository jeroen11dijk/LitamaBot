import java.util.HashMap;

public class TableBase {

    public HashMap<Integer, Integer> tablebase;

    public TableBase() {
        StringBuilder empty = new StringBuilder("0000000000000000000000000");
        empty.setCharAt(0, '1');
        Board board = Board.parseString(empty.toString());
    }
}
