package site.project.accountinfoapp.fintech.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.fintech.oauth.service.OauthService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@ApiResponse(description = "Oauth 인증")
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/callback")
    @Operation(summary = "Redirect 후에 사용자 토큰(3-legged) 을 반환받도록 함")
    public ResponseEntity<?> callbackPage(
            @RequestParam("code") String code
    ){
        return getToken(code);
    }

    @PostMapping("/callback")
    @Operation(summary = "사용자 토큰발급 API (3-legged) ")
    public ResponseEntity<?> getToken(
            @RequestParam("code") String code){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oauthService.provideToken(code));
    }

    @PostMapping( "/")
    @Operation(summary = "센터인증 이용기관 토큰발급 API (2-legged)")
    public ResponseEntity<?> getToken(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oauthService.provideToken());
    }
}