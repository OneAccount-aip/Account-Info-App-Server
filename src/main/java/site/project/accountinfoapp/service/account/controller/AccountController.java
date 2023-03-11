package site.project.accountinfoapp.service.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.project.accountinfoapp.service.account.service.AccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account")
    public ResponseEntity<?> allAccount(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findAll());
    }
}
