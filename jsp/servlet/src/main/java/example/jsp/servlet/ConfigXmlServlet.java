package example.jsp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigXmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletConfig config = this.getServletConfig();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("max count:" + config.getInitParameter("maxCount"));
    }
}
