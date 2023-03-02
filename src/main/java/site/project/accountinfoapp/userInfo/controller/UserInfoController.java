package site.project.accountinfoapp.userInfo.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.userInfo.service.UserInfoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@ApiResponse(description = "User 사용자 정보 조회")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/me")
    public ResponseEntity<?> retrieveUserInformation(
            @RequestHeader("Authorization") String token,
            @RequestParam("user_seq_no") String seqNo
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userInfoService.showUserInfo(token, seqNo));
    }
}
