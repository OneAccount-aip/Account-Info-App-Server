package site.project.accountinfoapp.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.project.accountinfoapp.oauth.service.OauthService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@ApiResponse(description = "Oauth 인증")
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/callback")
    @Operation(description = "사용자 토큰발급 API (3-legged) ")
    public ResponseEntity<?> getToken(
            @RequestParam("code") String code){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oauthService.provideToken(code));
    }

    @PostMapping( "/")
    @Operation(description = "센터인증 이용기관 토큰발급 API (2-legged)")
    public ResponseEntity<?> getToken(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oauthService.provideToken());
    }
}