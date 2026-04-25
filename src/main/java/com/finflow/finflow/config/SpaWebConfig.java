package com.finflow.finflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpaWebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String[] spaRoutes = {
                "/",
                "/despesas",
                "/receitas",
                "/categorias",
                "/metas",
                "/usuarios",
                "/logs"
        };

        for (String route : spaRoutes) {
            registry.addViewController(route).setViewName("forward:/index.html");
        }
    }
}
