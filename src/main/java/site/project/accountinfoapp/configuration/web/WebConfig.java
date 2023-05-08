package site.project.accountinfoapp.configuration.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static site.project.accountinfoapp.common.component.AppProperties.Cors;

@Configuration
@RequiredArgsConstructor
public class WebConfig{

    private final Cors cors;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://web-account-info-app-client-6g2llf5d1j9b.sel3.cloudtype.app/");
        config.addAllowedOrigin("https://oneaccount.kro.kr/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
