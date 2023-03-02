package site.project.accountinfoapp.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.account.service.AccountService;

import static site.project.accountinfoapp.account.dto.AccountRequestDto.RegisteredInfoRequestDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@ApiResponse(description = "Account 계좌 정보")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("")
    @Operation(description = "계좌정보 조회")
    public ResponseEntity<?> showAccount(
            @RequestHeader("Authorization") String token,
            @RequestParam("fintech_use_num") String finNum
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findOne(token, finNum));
    }

    @GetMapping("/list")
    @Operation(description = "등록계좌 조회")
    public ResponseEntity<?> showAllAccount(
            @RequestHeader("Authorization") String token,
            @RequestParam("user_seq_no") String seqNo,
            @RequestParam(value = "include_cancel_yn", defaultValue = "N") Character isInclude,
            @RequestParam(value = "sort_order", defaultValue = "D") Character sort
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findAll(token, new RegisteredInfoRequestDto(seqNo, isInclude, sort)));
    }

    @GetMapping("/transaction_list")
    @Operation(description = "거래내역 조회")
    public ResponseEntity<?> showTransactionHistory(
            @RequestHeader("Authorization") String token,
            @RequestParam("fintech_use_num") String finNum,
            @RequestParam(value = "from_date", defaultValue = "") String fromDate,
            @RequestParam(value = "to_date", defaultValue = "") String toDate
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findHistory(token, finNum, fromDate, toDate));
    }
}