package example.jsp;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/back")
public class WebSocketService {

    @OnOpen
    public void onOpen(Session session) throws IOException, InterruptedException {
        System.out.println("WebSocketService onOpen");
        this.session = session;
        Runnable runnable = new AsyncService(session);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @OnClose
    public void onClose() {
        System.out.println("WebSocketService onClose");
        this.session = null;
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("WebSocketService onMessage");
        String info = String.format("WebSocketService received message : %s", message);
        this.session.getBasicRemote().sendText(info);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("WebSocketService onError");
        error.printStackTrace();
    }

    private Session session;
}
