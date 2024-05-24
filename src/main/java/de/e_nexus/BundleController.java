package de.e_nexus;

import javax.inject.Inject;

import org.osgi.framework.BundleContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Report bundles in the context.
 */
@RestController
public class BundleController {
    /**
     * The karaf instance.
     */
    @Inject
    private final KarafInitializer karafInit = null;

    /**
     * List all bundles in karaf.
     *
     * @return A render payload for the bundle-table.
     */
    @GetMapping("/")
    public ModelAndView tableContent() {
        BundleContext ctx = karafInit.getFramework().getBundleContext();
        return new ModelAndView("views/index", "bundles", ctx.getBundles());
    }
}
