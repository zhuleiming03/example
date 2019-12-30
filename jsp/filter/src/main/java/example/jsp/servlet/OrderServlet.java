package example.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderServlet",urlPatterns = {"/order/*"})
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("--------------------------------\n\n\n");
        response.getWriter().write("This is a Order page \n");
        response.getWriter().write("FirstFilter and SecondFilter is web.xml\n");
        response.getWriter().write("ThirdFilter is listener (true) \n");
        response.getWriter().write("\n\n\n--------------------------------\n");

    }
}
