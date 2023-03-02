package site.project.accountinfoapp.configuration;

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
}
