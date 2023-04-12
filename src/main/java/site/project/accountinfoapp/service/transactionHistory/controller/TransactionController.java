package site.project.accountinfoapp.service.transactionHistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.service.transactionHistory.dto.TransactionRequestDto.TransferRequestDto;
import site.project.accountinfoapp.service.transactionHistory.service.TransactionService;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transaction/user")
    @Operation(summary = "해당 유저의 거래내역 조회")
    public ResponseEntity<?> getTransHistory(
            @RequestParam(value = "num") String finNum){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getHistory(finNum));
    }

    @PostMapping("/transaction/transfer")
    @Operation(summary = "송금이체 처리, 출금 후 입금이체")
    public ResponseEntity<?> transfer(
            @RequestBody TransferRequestDto dto
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.transferToUser(dto));
    }

    @PostMapping("/transaction/withdraw")
    @Operation(summary = "입금이체")
    public ResponseEntity<?> withDraw(
            @RequestBody TransferRequestDto dto
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.withdrawToService(dto));
    }
}
