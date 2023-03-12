package site.project.accountinfoapp.configuration.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static site.project.accountinfoapp.common.component.AppProperties.*;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final Cors cors;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://web-account-info-app-client-6g2llf5d1j9b.sel3.cloudtype.app/")
                .allowedHeaders("Authorization");
    }
}
