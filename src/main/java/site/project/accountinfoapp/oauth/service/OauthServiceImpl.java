package site.project.accountinfoapp.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.api.OauthOpenFeign;
import site.project.accountinfoapp.oauth.dto.AuthRequestDto.Token2LeggedRequestDto;
import site.project.accountinfoapp.oauth.dto.AuthRequestDto.Token3LeggedRequestDto;

import static site.project.accountinfoapp.configuration.AppProperties.GrantType;
import static site.project.accountinfoapp.configuration.AppProperties.Oauth;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{

    private final OauthOpenFeign openFeign;

    private final Oauth oauth;
    private final GrantType grantType;

    @Override
    public Object provideToken() {
        return openFeign.get2LeggedToken(new Token2LeggedRequestDto(
            oauth.getClient_id(),
            oauth.getClient_secret(),
            oauth.getScope(),
            grantType.getClient_credentials()
            ));
    }

    @Override
    public Object provideToken(String code) {
        return openFeign.get3LeggedToken(new Token3LeggedRequestDto(
            code,
            oauth.getClient_id(),
            oauth.getClient_secret(),
            oauth.getRedirect_uri(),
            grantType.getAuthorization_code()
        ));
    }
}
