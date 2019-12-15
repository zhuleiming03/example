package example.jsp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "visitServlet",urlPatterns = "/visit/jsp")
public class VisitServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Map<String, String[]> paras = request.getParameterMap();

        paras.forEach((key, value) -> {
            System.out.println(String.format("Post request :[%s:%s]", key, Arrays.toString(value)));
        });

        request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "instructions":
                request.getRequestDispatcher("/WEB-INF/jsp/instructions.jsp").forward(request, response);
                break;
            case "declare":
                request.getRequestDispatcher("/WEB-INF/jsp/declare.jsp").forward(request, response);
                break;
            case "script":
                request.getRequestDispatcher("/WEB-INF/jsp/script.jsp").forward(request, response);
                break;
            case "expression":
                request.getRequestDispatcher("/WEB-INF/jsp/expression.jsp").forward(request, response);
                break;
            case "note":
                request.getRequestDispatcher("/WEB-INF/jsp/note.jsp").forward(request, response);
                break;
            case "user":
                request.getRequestDispatcher("/WEB-INF/jsp/user.jsp").forward(request, response);
                break;
            default:
                response.getWriter().println("Hello world!");
                break;
        }
    }
}
