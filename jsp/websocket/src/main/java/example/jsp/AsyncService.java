package example.jsp;

import javax.websocket.Session;

public class AsyncService implements Runnable {

    public AsyncService(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        System.out.println("AsyncService thread begin !");
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(10_000L);
                String info = String.format("WebSocketService %dst send data...", i);
                if (!this.session.isOpen()) {
                    break;
                }
                this.session.getBasicRemote().sendText(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("AsyncService thread end !");
    }

    private Session session;
}
