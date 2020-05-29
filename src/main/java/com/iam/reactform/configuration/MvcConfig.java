package com.iam.reactform.configuration;

import com.sun.javafx.scene.control.skin.LabeledText;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }
}
