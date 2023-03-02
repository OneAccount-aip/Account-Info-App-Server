package site.project.accountinfoapp.api;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.project.accountinfoapp.oauth.dto.AuthRequestDto.Token2LeggedRequestDto;
import site.project.accountinfoapp.oauth.dto.AuthRequestDto.Token3LeggedRequestDto;

@FeignClient(name = "Oauth", url = "https://testapi.openbanking.or.kr")
public interface OauthOpenFeign{

    @PostMapping(path = "/oauth/2.0/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=UTF-8")
    Object get3LeggedToken(
            @RequestBody @SpringQueryMap Token3LeggedRequestDto dto
            );

    @PostMapping(path = "/oauth/2.0/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=UTF-8")
    Object get2LeggedToken(
            @RequestBody @SpringQueryMap Token2LeggedRequestDto dto
            );
}