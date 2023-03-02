package site.project.accountinfoapp.account.service;

import site.project.accountinfoapp.account.dto.AccountRequestDto;

public interface AccountService {

    Object findOne(String token, String finNum);
    Object findAll(String token, AccountRequestDto.RegisteredInfoRequestDto param);
    Object findHistory(String token, String finNum, String fromDate, String toDate);
}
