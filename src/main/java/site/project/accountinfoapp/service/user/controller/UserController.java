package site.project.accountinfoapp.service.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.project.accountinfoapp.service.user.dto.UserDto.UserSignupRequestDto;
import site.project.accountinfoapp.service.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequestDto user) throws Exception {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signup(user));
    }

    @GetMapping("/check")
    @Operation(summary = "인증된 계정인지 확인 T/F")
    public ResponseEntity<?> isCertified() throws Exception {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.checkToken());
    }

    @GetMapping("/user")
    @Operation(summary = "마이페이지 유저 정보 반환")
    public ResponseEntity<?> showMyInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getInfo());
    }

}
