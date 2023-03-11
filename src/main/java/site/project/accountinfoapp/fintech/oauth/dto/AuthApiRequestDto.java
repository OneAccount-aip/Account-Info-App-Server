package site.project.accountinfoapp.fintech.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public record AuthApiRequestDto(
) {
    @Data
    @AllArgsConstructor
    public static class Token3LeggedRequestDto{
            String code;
            String client_id;
            String client_secret;
            String redirect_uri;
            String grant_type;
    }

    @Data
    @AllArgsConstructor
    public static class Token2LeggedRequestDto{
            String client_id;
            String client_secret;
            String scope;
            String grant_type;
    }
}
