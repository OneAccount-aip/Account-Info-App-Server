package site.project.accountinfoapp.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import site.project.accountinfoapp.configuration.principalDetaills.PrincipalDetails;
import site.project.accountinfoapp.service.user.domain.User;
import site.project.accountinfoapp.service.user.repository.UserRepository;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.Optional;

import static site.project.accountinfoapp.common.component.AppProperties.Jwt;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

    private final UserRepository userRepository;
    private final Jwt jwt;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, Jwt jwt) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwt = jwt;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(jwt.getHEADER_STRING());
        if(header == null || !header.startsWith(jwt.getTOKEN_PREFIX())) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(jwt.getHEADER_STRING())
                .replace(jwt.getTOKEN_PREFIX()+" ", "");
        try {
            String username = JWT.require(Algorithm
                            .HMAC512(jwt.getSECRET()))
                    .build()
                    .verify(token)
                    .getClaim("username").asString();

            if (username != null) {
                Optional<User> user = userRepository.findByUsername(username);
                if (user.isEmpty())
                    throw new TokenExpiredException("");
                PrincipalDetails principalDetails = new PrincipalDetails(user.get());
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                principalDetails,
                                null,
                                principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            try {
                throw new AuthenticationException();
            } catch (org.springframework.security.core.AuthenticationException ex) {
                ex.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }
}
