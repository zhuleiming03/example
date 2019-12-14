package example.jsp.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "contextServlet",urlPatterns = "/context")
public class ContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = this.getServletContext();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("MS Data url:" + context.getInitParameter("dateBase"));
    }
}
