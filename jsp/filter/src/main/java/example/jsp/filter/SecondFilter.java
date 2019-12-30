package example.jsp.filter;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.getWriter().write("SecondFilter need filter \n");
        chain.doFilter(req, resp);
        resp.getWriter().write("SecondFilter has execute \n");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
