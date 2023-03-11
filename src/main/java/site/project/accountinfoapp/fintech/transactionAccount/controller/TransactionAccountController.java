package site.project.accountinfoapp.fintech.transactionAccount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiRequestDto;
import site.project.accountinfoapp.fintech.transactionAccount.service.TransactionAccountService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction/account")
@ApiResponse(description = "Account 계좌 정보")
public class TransactionAccountController {

    private final TransactionAccountService accountService;

    @GetMapping("")
    @Operation(summary = "계좌정보 조회")
    public ResponseEntity<?> showAccount(
            @RequestParam("fintech_use_num") String finNum
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findOne(finNum));
    }

    @GetMapping("/list")
    @Operation(summary = "등록계좌 조회")
    public ResponseEntity<?> showAllAccount(
            @RequestHeader("Authorization") String token,
            @RequestParam("user_seq_no") String seqNo,
            @RequestParam(value = "include_cancel_yn", defaultValue = "N") Character isInclude,
            @RequestParam(value = "sort_order", defaultValue = "D") Character sort
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findAll(token, new AccountApiRequestDto.RegisteredInfoRequestDto(seqNo, isInclude, sort)));
    }

    @GetMapping("/transaction_list")
    @Operation(summary = "거래내역 조회")
    public ResponseEntity<?> showTransactionHistory(
            @RequestParam("fintech_use_num") String finNum,
            @RequestParam(value = "from_date", defaultValue = "") String fromDate,
            @RequestParam(value = "to_date", defaultValue = "") String toDate
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.findHistory(finNum, fromDate, toDate));
    }
}