package site.project.accountinfoapp.common.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public record AppProperties() {

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.oauth")
    public static class Oauth{
        String client_id;
        String client_secret;
        String scope;
        String redirect_uri;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.grant")
    public static class GrantType{
        String client_credentials;
        String authorization_code;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.tranid")
    public static class TranId{
        String header_string;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.account")
    public static class Account{
        String recv_client_name;
        String recv_client_bank_code;
        String recv_client_account_num;
        String sub_frnc_name;
        String sub_frnc_num;
        String sub_frnc_business_num;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.cors")
    public static class Cors{
        String test_origin;
        String production_origin;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.jwt")
    public static class Jwt {
        private String SECRET;
        private int EXPIRATION_TIME;
        private String TOKEN_PREFIX;
        private String HEADER_STRING;
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.center")
    public static class Center {
        private String account_num;

    }
}