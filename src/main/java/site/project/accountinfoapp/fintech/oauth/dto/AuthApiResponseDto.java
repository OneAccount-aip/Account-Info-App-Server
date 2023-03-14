package site.project.accountinfoapp.fintech.oauth.dto;

import lombok.Data;

public record AuthApiResponseDto(
) {
    @Data
    public static class Token3LeggedResponseDto{
        String access_token;
        String token_type;
        String refresh_token;
        Float expires_in;
        String scope;
        String user_seq_no;
        String rsp_code;
        String rsp_message;
    }

    @Data
    public static class Token2LeggedResponseDto {
        String access_token;
        String token_type;
        Float expires_in;
        String scope;
        String client_use_code;
        String rsp_code;
        String rsp_message;
    }
}
