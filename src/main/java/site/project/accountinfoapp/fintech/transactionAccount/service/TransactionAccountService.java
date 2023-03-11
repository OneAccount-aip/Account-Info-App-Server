package site.project.accountinfoapp.fintech.transactionAccount.service;

import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiRequestDto.RegisteredInfoRequestDto;

public interface TransactionAccountService {

    Object findOne(String finNum);
    Object findAll(String token, RegisteredInfoRequestDto param);
    Object findHistory(String finNum, String fromDate, String toDate);
}
