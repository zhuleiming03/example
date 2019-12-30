package example.jsp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "annotate",urlPatterns = "/article",servletNames = "ArticleServlet")
public class AnnotateFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("AnnotateFilter is destroy");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        System.out.println("AnnotateFilter is doFilter");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("AnnotateFilter is init");
    }
}
