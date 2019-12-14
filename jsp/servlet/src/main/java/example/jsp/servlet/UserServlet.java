package example.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "userServlet",urlPatterns = {"/query/user","/find/user"})
public class UserServlet extends HttpServlet {

    private static final String DEFAULT_USER = "Guest";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> paras = request.getParameterMap();

        paras.forEach((key, value) -> {
            System.out.println(String.format("Post request :[%s:%s]", key, Arrays.toString(value)));
        });

        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        if (user == null) {
            user = UserServlet.DEFAULT_USER;
        }

        System.out.println("Get request :" + user);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("<head>\r\n")
                .append("<title>This is a test!</title>")
                .append("</head>\r\n")
                .append("<body>\r\n")
                .append("Hello,").append(user).append("!<br/><br/>\r\n")
                .append("<form action=\"../find/user\" method=\"POST\">\r\n")
                .append("Enter your name:<br/>\r\n")
                .append("<input type=\"text\" name=\"user\"><br/>\r\n")
                .append("<input type=\"submit\" value=\"Submit\"><br/>\r\n")
                .append("<form/>\r\n")
                .append("</body>\r\n")
                .append("</html>\r\n");
    }
}
