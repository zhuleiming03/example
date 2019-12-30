package example.jsp.filter;

import javax.servlet.*;
import java.io.IOException;

public class CodeFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("CodeFilter is destroy");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        System.out.println("CodeFilter is doFilter");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("CodeFilter is init");
    }

}
