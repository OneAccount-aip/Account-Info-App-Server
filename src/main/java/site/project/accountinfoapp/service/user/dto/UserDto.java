package site.project.accountinfoapp.service.user.dto;

import java.time.LocalDate;

public record UserDto (){

    public record UserSigninRequestDto(
            String username,
            String password
    ){
    }

    public record UserSignupRequestDto (
             String username,
             String password,
             String email
    ){
    }

    public record UserTokenRequestDto(
            String cntrToken,
            String refreshToken,
            String depositToken,
            String userSeqNo,
            String clientUseCode
    ){}

    public record UserInfoResponseDto(
            String name,
            String email,
            Boolean isCertified,
            LocalDate createAt
    ){}
}
