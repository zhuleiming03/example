package example.spring;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Bootstrap implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {
        XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        rootContext.setConfigLocation("/WEB-INF/rootContext.xml");
        container.addListener(new ContextLoaderListener(rootContext));

        XmlWebApplicationContext servletContext = new XmlWebApplicationContext();
        servletContext.setConfigLocation("/WEB-INF/servletContext.xml");
        ServletRegistration.Dynamic dispatcher = container.addServlet("springDispatcher",
                new DispatcherServlet(servletContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
