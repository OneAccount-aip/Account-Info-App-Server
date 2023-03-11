package site.project.accountinfoapp.fintech.oauth.service;

public interface OauthService {
    Object provideToken();
    Object provideToken(String code);
}