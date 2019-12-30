package example.jsp.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AsyncServlet",urlPatterns = "/async",asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int id;
        synchronized (AsyncServlet.class) {
            id = ID++;
        }
        long timeout = request.getParameter("timeout") == null ?
                10000L : Long.parseLong(request.getParameter("timeout"));

        System.out.println("[Servlet] Entering AsyncServlet.doGet(). Request ID = " + id +
                ", isAsyncStarted = " + request.isAsyncStarted());

        final AsyncContext context = request.getParameter("unwrap") != null ?
                request.startAsync() : request.startAsync(request, response);
        context.setTimeout(timeout);

        System.out.println("Starting asynchronous thread. Request ID = " + id +
                ".");

        AsyncThread thread = new AsyncThread(id, context);
        context.start(thread::doWork);

        System.out.println("[Servlet] Leaving AsyncServlet.doGet(). Request ID = " + id +
                ", isAsyncStarted = " + request.isAsyncStarted());
    }

    private static class AsyncThread {
        private final int id;
        private final AsyncContext context;

        public AsyncThread(int id, AsyncContext context) {
            this.id = id;
            this.context = context;
        }

        public void doWork() {

            System.out.println(String.format("[thread] started. Request ID = %d .",
                    this.id));

            try {
                Thread.sleep(5_000L);
            } catch (Exception e) {
                e.printStackTrace();
            }

            HttpServletRequest request = (HttpServletRequest) this.context.getRequest();

            System.out.println(String.format("Done sleeping. Request ID = %d , URL = %s .",
                    this.id, request.getRequestURL()));

            this.context.dispatch("/WEB-INF/jsp/async.jsp");

            System.out.println(String.format("[thread] completed. Request ID = %d .",
                    this.id));
        }
    }

    private static volatile int ID = 1;
}
