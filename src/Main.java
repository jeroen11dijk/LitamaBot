import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        WebSocketClient client = null;
        try {
            client = new OnitamaClient(new URI("wss://litama.herokuapp.com"));
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Piece[][] parseString(String board) {
        Piece[][] res = new Piece[5][5];
        String[] boardArray =  board.split("(?<=\\G.{5})");
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                int piece = Character.getNumericValue(boardArray[y].charAt(x));
                if (piece == 0) {
                    res[y][x] = Piece.EMPTY;
                }
                if (piece == 1) {
                    res[y][x] = Piece.BLUE;
                }
                if (piece == 2) {
                    res[y][x] = Piece.BLUEFIRE;
                }
                if (piece == 3) {
                    res[y][x] = Piece.RED;
                }
                if (piece == 4) {
                    res[y][x] = Piece.REDOX;
                }
            }
        }
        return res;
    }

    // blue = ox, boar, red = horse, elephant, side = crab

//    int evaluate() {
//        Color skipTurn = this.turn == Color.RED ? Color.BLUE : Color.RED;
//        Hand skipHand = this.turn == Color.RED ? this.blue : this.red;
//        int res = 0;
//        int[][] canReach = new int[5][5];
//        for (int y = 0; y < 5; y++) {
//            for (int x = 0; x < 5; x++) {
//                if (this.board.board[y][x].color == skipTurn) {
//                    res += 3;
//                    for (Offset offset : skipHand.first.offsets) {
//                        if (this.board.validMove(offset, x, y)) {
//                            canReach[y + offset.y][x + offset.x] = 1;
//                        }
//                    }
//                    for (Offset offset : skipHand.second.offsets) {
//                        if (this.board.validMove(offset, x, y)) {
//                            canReach[y + offset.y][x + offset.x] = 1;
//                        }
//                    }
//                } else if (this.board.board[y][x].color == this.turn) {
//                    res -= 3;
//                }
//            }
//        }
//        for (int[] column : canReach) {
//            for (int i : column) {
//                res += 2 * i;
//            }
//        }
//        return res;
//    }
}
