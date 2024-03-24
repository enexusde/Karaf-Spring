package de.e_nexus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

/**
 * The basic configuration of Spring.
 */
@EnableWebMvc
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig implements WebApplicationInitializer {
    /**
     * Start Spring's {@link AnnotationConfigWebApplicationContext}.
     */
    @Override
    public void onStartup(final ServletContext servletContext)
            throws ServletException {
        servletContext.addListener(ContextLoaderListener.class);
        servletContext.setInitParameter("contextClass",
                AnnotationConfigWebApplicationContext.class.getCanonicalName());
        servletContext.setInitParameter("contextConfigLocation",
                AppConfig.class.getCanonicalName());
    }
}
