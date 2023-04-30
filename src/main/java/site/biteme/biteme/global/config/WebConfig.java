package site.biteme.biteme.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.biteme.biteme.global.converter.DepartmentConverter;
import site.biteme.biteme.global.converter.MajorConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
     @Override
     public void addFormatters(FormatterRegistry registry) {
         registry.addConverter(new MajorConverter());
         registry.addConverter(new DepartmentConverter());
     }



}










