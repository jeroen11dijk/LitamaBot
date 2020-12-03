import java.util.HashMap;

public class TableBase {

    public HashMap<Board, Integer> tablebase;

    public TableBase() {
        tablebase = new HashMap<>();
        generateMateIn0();
        System.out.println(tablebase.size());
    }

    public void generateMateIn0() {
        StringBuilder capture = new StringBuilder("0000000000000000000000000");
        StringBuilder temple = new StringBuilder("0000000000000000000000200");
        for (int i = 0; i < capture.length(); i++) {
            StringBuilder copyCapture = new StringBuilder(capture);
            copyCapture.setCharAt(i, '2');
            tablebase.put(new Board(copyCapture.toString()), 0);
            if(temple.charAt(i) != '2') {
                StringBuilder copyTemple = new StringBuilder(capture);
                copyTemple.setCharAt(i, '4');
                tablebase.put(new Board(copyTemple.toString()), 0);
            }
        }
    }
}
