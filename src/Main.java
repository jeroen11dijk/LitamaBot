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
    // blue = ox, boar, red = horse, elephant, side = crab
}
