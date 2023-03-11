package site.project.accountinfoapp.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.project.accountinfoapp.common.component.AppProperties;
import site.project.accountinfoapp.configuration.principalDetaills.PrincipalDetails;
import site.project.accountinfoapp.service.user.dto.UserDto;

import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final AppProperties.Jwt jwt;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        UserDto.UserSigninRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), UserDto.UserSigninRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert loginRequestDto != null;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.username(),
                        loginRequestDto.password());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult){

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwt.getEXPIRATION_TIME()))
                .withClaim("id", principalDetails.getUser().getId().toString())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(jwt.getSECRET()));
        response.addHeader(jwt.getHEADER_STRING(), jwt.getTOKEN_PREFIX() +" "+ jwtToken);
    }
}
