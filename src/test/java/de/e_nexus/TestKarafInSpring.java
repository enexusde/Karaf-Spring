package de.e_nexus;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.context.ContextLoaderListener;

import jakarta.servlet.ServletContext;

public class TestKarafInSpring {
    @ComponentScan(basePackageClasses = AppConfig.class, excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AppConfig.class) })
    public static class LimitedAppConfig {

    }

    public void testAppConfig() throws Exception {
        AppConfig ac = new AppConfig();
        List<Object[]> values = new ArrayList<>();
        ac.onStartup((ServletContext) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[] {ServletContext.class },
                (proxy, method, args) -> values.add(args)));
        assert ContextLoaderListener.class
                .isAssignableFrom((Class<?>) values.get(0)[0]);
        assert ApplicationContext.class
                .isAssignableFrom(Class.forName(values.get(1)[1].toString()));
        assert AppConfig.class
                .isAssignableFrom(Class.forName(values.get(2)[1].toString()));
    }

    public void testKarafStart() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                LimitedAppConfig.class);
        ctx.start();
    }
}
