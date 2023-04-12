package site.project.accountinfoapp.service.kftcAuth.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.service.kftcAuth.service.AuthService;
import site.project.accountinfoapp.service.user.dto.UserDto.UserTokenRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    @Operation(summary = "Kftc 이용 토큰 등록")
    public ResponseEntity<?> registerToken(@RequestBody UserTokenRequestDto tokens){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.authenticate(tokens));
    }
}
