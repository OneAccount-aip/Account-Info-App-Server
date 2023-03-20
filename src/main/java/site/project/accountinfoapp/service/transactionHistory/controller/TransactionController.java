package site.project.accountinfoapp.service.transactionHistory.controller;

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
    public ResponseEntity<?> getTransHistory(
            @RequestParam(value = "num") String finNum){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getHistory(finNum));
    }

    @PostMapping("/transaction/transfer")
    public ResponseEntity<?> transfer(
            @RequestBody TransferRequestDto dto
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.transfer(dto));
    }
}
