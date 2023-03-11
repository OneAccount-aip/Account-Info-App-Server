package site.project.accountinfoapp.fintech.oauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.fintech.oauth.dto.AuthApiRequestDto;
import site.project.accountinfoapp.fintech.oauth.feignClient.OauthOpenFeign;

import static site.project.accountinfoapp.common.component.AppProperties.GrantType;
import static site.project.accountinfoapp.common.component.AppProperties.Oauth;
import static site.project.accountinfoapp.fintech.oauth.dto.AuthApiRequestDto.*;

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
