package site.project.accountinfoapp.fintech.oauth.feignClient;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.project.accountinfoapp.fintech.oauth.dto.AuthApiRequestDto;
import site.project.accountinfoapp.fintech.oauth.dto.AuthApiResponseDto;

import static site.project.accountinfoapp.fintech.oauth.dto.AuthApiRequestDto.*;
import static site.project.accountinfoapp.fintech.oauth.dto.AuthApiResponseDto.*;

@FeignClient(name = "Oauth", url = "https://testapi.openbanking.or.kr")
public interface OauthOpenFeign{

    @PostMapping(path = "/oauth/2.0/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=UTF-8")
    Token3LeggedResponseDto get3LeggedToken(
            @RequestBody @SpringQueryMap Token3LeggedRequestDto dto
            );

    @PostMapping(path = "/oauth/2.0/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=UTF-8")
    Token2LeggedResponseDto get2LeggedToken(
            @RequestBody @SpringQueryMap Token2LeggedRequestDto dto
            );
}