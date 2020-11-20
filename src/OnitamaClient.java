import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;

public class OnitamaClient extends WebSocketClient {

    private String token;

    public OnitamaClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public OnitamaClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("create jeronbot");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
        JSONObject json = new JSONObject(message);
        String matchid = json.getString("matchId");
        if (json.getString("messageType").equals("create")) {
            System.out.println("https://git.io/onitama#spectate-" + matchid);
            token = json.getString("token");
            send("spectate " + matchid);
//            send("join " + matchid + " jeronBot");
        } else if (json.getString("messageType").equals("state")) {
            if (!json.getString("gameState").equals("waiting for player")) {
                JSONObject usernames = json.getJSONObject("usernames");
                String currentTurn = json.getString("currentTurn");
                if (usernames.getString(currentTurn).equals("jeronbot")) {
                    Color turn = currentTurn.equals("red") ? Color.RED : Color.BLUE;
                    JSONObject cards = json.getJSONObject("cards");
                    JSONArray blueCards = cards.getJSONArray("blue");
                    JSONArray redCards = cards.getJSONArray("red");
                    Hand blueHand = new Hand(Card.getCard(blueCards.get(0).toString()),Card.getCard(blueCards.get(1).toString()), Color.BLUE);
                    Hand redHand = new Hand(Card.getCard(redCards.get(0).toString()),Card.getCard(redCards.get(1).toString()), Color.RED);
                    Card middle = Card.getCard(cards.getString("side"));
                    Piece[][] boardPiece = Main.parseString(json.getString("board"));
                    Board board = new Board(boardPiece);
                    Game game = new Game(board, turn, Color.BLUE, redHand, blueHand, middle);
                    Move move = game.negamaxRoot(10);
                    String card = move.card.name.toLowerCase();
                    String origin = getCharForNumber(move.x + 1) + (move.y + 1);
                    String target = getCharForNumber(move.x + move.offset.x + 1) + (move.y + move.offset.y + 1);
                    String moveString = origin + target;
                    send("move " + matchid + " " + token + " " + card + " " + moveString);
                }
            }
        }
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)).toLowerCase() : null;
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }
}