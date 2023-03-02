package site.project.accountinfoapp.oauth.dto;

public record AuthRequestDto(
) {
    public record Token3LeggedRequestDto(
            String code,
            String client_id,
            String client_secret,
            String redirect_uri,
            String grant_type
    ){
    }

    public record Token2LeggedRequestDto(
            String client_id,
            String client_secret,
            String scope,
            String grant_type
    ){
    }
}
