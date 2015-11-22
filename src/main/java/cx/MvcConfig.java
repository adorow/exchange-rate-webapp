package cx;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view").setViewName("cx/view");
        registry.addViewController("/").setViewName("cx/view");
        registry.addViewController("/login").setViewName("cx/login");
        registry.addViewController("/registration").setViewName("cx/registration");
    }

}
