package com.tiger.CosineSearch;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
           .allowedOrigins("https://csamma-front.herokuapp.com","http://localhost:3000") 
           .allowedMethods("GET", "POST", "PUT", "DELETE")
           .allowCredentials(false).maxAge(3600)
       	   .allowedHeaders("*");
   }
}
