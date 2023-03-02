package site.project.accountinfoapp.oauth.service;

public interface OauthService {
    Object provideToken();
    Object provideToken(String code);
}