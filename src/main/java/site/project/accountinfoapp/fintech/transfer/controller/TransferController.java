package site.project.accountinfoapp.fintech.transfer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.WithDrawRequestDto;
import site.project.accountinfoapp.fintech.transfer.service.TransferService;

import static site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account/transfer")
@ApiResponse(description = "Transfer 이체")
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/withdraw")
    @Operation(summary = "출금이체")
    public ResponseEntity<?> withdrawFromAccount(
            @RequestBody WithDrawRequestDto dto
            ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transferService.withDraw(dto));
    }

    @PostMapping("/deposit")
    @Operation(summary = "입금이체")
    public ResponseEntity<?> depositToAccount(
            @RequestBody DepositRequestDto dto
    ){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transferService.deposit(dto));
    }
}