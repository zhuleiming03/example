package example.jsp.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "conifgAnnotationServlet",urlPatterns = "/config/annotation",
        initParams = {@WebInitParam(name ="minCount",value = "9")})
public class ConifgAnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = this.getServletConfig();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("min count:" + config.getInitParameter("minCount"));
    }
}
