package de.e_nexus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.groovy.GroovyMarkupConfigurer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

/**
 * The basic configuration of Spring.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig implements WebApplicationInitializer, WebMvcConfigurer {
    /**
     * Start Spring's {@link AnnotationConfigWebApplicationContext}.
     */
    @Override
    public void onStartup(final ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext context = // add AppContext
                new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        ServletRegistration.Dynamic registration = ctx.addServlet(
                "dispatcherServlet", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }

    /**
     * Register groovy as view resolver.
     */
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        registry.groovy();
    }

    /**
     * The groovy configuration.
     *
     * @return The groovy configuration
     */
    @Bean
    public GroovyMarkupConfigurer groovyMarkupConfigurer() {
        GroovyMarkupConfigurer configurer = new GroovyMarkupConfigurer();
        configurer.setResourceLoaderPath("/WEB-INF/");
        return configurer;
    }
}
