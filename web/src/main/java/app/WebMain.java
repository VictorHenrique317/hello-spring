package app;

import app.utils.ViewNames;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebMain implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName(ViewNames.HOME);
        registry.addViewController("/").setViewName(ViewNames.HOME);
    }
}
