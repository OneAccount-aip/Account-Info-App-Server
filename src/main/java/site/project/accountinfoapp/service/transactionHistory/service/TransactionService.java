package site.project.accountinfoapp.service.transactionHistory.service;

import site.project.accountinfoapp.service.transactionHistory.dto.TransactionRequestDto.TransferRequestDto;

public interface TransactionService {

    Object getHistory(String finNum);
    Object transferToUser(TransferRequestDto dto);
    Object withdrawToService(TransferRequestDto dto);
}
