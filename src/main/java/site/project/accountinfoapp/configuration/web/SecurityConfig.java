package site.project.accountinfoapp.configuration.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import site.project.accountinfoapp.common.component.AppProperties.Jwt;
import site.project.accountinfoapp.common.exception.JwtAccessDeniedHandler;
import site.project.accountinfoapp.common.exception.JwtAuthenticationEntryPoint;
import site.project.accountinfoapp.configuration.jwt.JwtAuthenticationFilter;
import site.project.accountinfoapp.configuration.jwt.JwtAuthorizationFilter;
import site.project.accountinfoapp.service.user.repository.UserRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAccessDeniedHandler handler;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final Jwt jwt;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChainConfig(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()

                .apply(new MyCustomDsl())
                .and()

                .exceptionHandling()
                .accessDeniedHandler(handler)
                .authenticationEntryPoint(entryPoint)
                .and()

                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }

    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(new JwtAuthenticationFilter(authenticationManager, jwt))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository, jwt));
        }
    }
}
