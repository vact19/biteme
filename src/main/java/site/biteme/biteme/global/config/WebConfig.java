package site.biteme.biteme.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.biteme.biteme.global.converter.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
     @Override
     public void addFormatters(FormatterRegistry registry) {
         registry.addConverter(new CategoryConverter());
         registry.addConverter(new DepartmentConverter());
         registry.addConverter(new MajorConverter());
         registry.addConverter(new RankConverter());
         registry.addConverter(new GradeConverter());
     }



}










