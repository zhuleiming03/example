package example.jsp.filter;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        Instant time = Instant.now();

        try {
            chain.doFilter(request, response);
        } finally {
            HttpServletRequest in = (HttpServletRequest) request;
            HttpServletResponse out = (HttpServletResponse) response;
            String length = out.getHeader("Content-Length");
            if (length == null || length.length() == 0) {
                length = "-";
            }
            System.out.println(in.getRemoteAddr() + " - - [" + time + "]" +
                    " \"" + in.getMethod() + " " + in.getRequestURI() + " " +
                    in.getProtocol() + "\" " + out.getStatus() + " " + length);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
