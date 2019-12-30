package example.jsp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@WebFilter(filterName = "async")
public class AnyRequestFilter implements Filter {
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println(String.format("[Filter]:Entering %s.doFilter().",
                this.name));

        chain.doFilter(
                new HttpServletRequestWrapper((HttpServletRequest) req),
                new HttpServletResponseWrapper((HttpServletResponse) resp)
        );
        if (req.isAsyncSupported() && req.isAsyncStarted()) {
            AsyncContext context = req.getAsyncContext();

            System.out.println(String.format("[Filter]:Leaving %s.doFilter(), async context holds wrapped request/response =%s .",
                    this.name, !context.hasOriginalRequestAndResponse()));

        } else {
            System.out.println(String.format("[Filter]:Leaving %s.doFilter().",
                    this.name));
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.name = config.getFilterName();
    }

    private String name;
}
