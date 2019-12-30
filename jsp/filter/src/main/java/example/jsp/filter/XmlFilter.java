package example.jsp.filter;

import javax.servlet.*;
import java.io.IOException;

public class XmlFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("XmlFilter is destroy");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        System.out.println("XmlFilter is doFilter");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("XmlFilter is init");
    }
}
