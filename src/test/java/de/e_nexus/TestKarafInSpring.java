package de.e_nexus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

public class TestKarafInSpring {
    @ComponentScan(basePackageClasses = AppConfig.class, excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AppConfig.class) })
    public static class LimitedAppConfig {

    }

    public void testKarafStart() throws Exception {

        KarafInitializer emptyKaraf = new KarafInitializer();
        assert !emptyKaraf.isRunning();
        assert emptyKaraf.getFramework() == null;

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                LimitedAppConfig.class);
        ctx.start();
        KarafInitializer ki = ctx.getBean(KarafInitializer.class);
        assert ki.isRunning();
        assert !ki.remove("Me not exists");
        ki.stop();
    }
}
