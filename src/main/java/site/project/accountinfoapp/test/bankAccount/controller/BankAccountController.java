package site.project.accountinfoapp.test.bankAccount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.project.accountinfoapp.test.bankAccount.service.BankAccountService;

@RestController
@RequiredArgsConstructor
@ApiResponse(description = "등록된 은행 계좌를 다룸")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/bankAccount")
    @Operation(summary = "계좌번호로 계좌 정보를 가져옴")
    public ResponseEntity<?>retrieveAccount(
            @RequestParam(value = "num") String number
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bankAccountService.retrieveContainingAccount(number));
    }

    @GetMapping("/finNum")
    @Operation(summary = "핀테크 번호로 계좌 정보를 가져옴")
    public ResponseEntity<?>retrieveAccountByFinNUm(
            @RequestParam(value = "num") String number
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bankAccountService.retrieveAccountByFinNum(number));
    }
}
