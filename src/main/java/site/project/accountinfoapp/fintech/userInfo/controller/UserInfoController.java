package site.project.accountinfoapp.fintech.userInfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.fintech.userInfo.service.UserInfoService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@ApiResponse(description = "User 사용자 정보 조회")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/me")
    @Operation(summary = "사용자 정보와 계좌 목록 조회")
    public ResponseEntity<?> retrieveUserInformation() throws ChangeSetPersister.NotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userInfoService.showUserInfo());
    }
}
