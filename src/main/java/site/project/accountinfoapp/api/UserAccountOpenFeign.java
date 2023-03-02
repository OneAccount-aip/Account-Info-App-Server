package site.project.accountinfoapp.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import site.project.accountinfoapp.account.dto.AccountRequestDto.AccountInfoRequestDto;
import site.project.accountinfoapp.account.dto.AccountRequestDto.RegisteredInfoRequestDto;
import site.project.accountinfoapp.account.dto.AccountRequestDto.TransactionHistoryRequestDto;

@FeignClient(name = "Account", url = "https://testapi.openbanking.or.kr/")
public interface UserAccountOpenFeign {

    @GetMapping("/v2.0/account/balance/fin_num")
    Object retrieveUserInfo(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam @SpringQueryMap AccountInfoRequestDto dto
            );

    @GetMapping("/v2.0/account/list")
    Object retrieveRegisteredAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam @SpringQueryMap RegisteredInfoRequestDto dto
    );

    @GetMapping("/v2.0/account/transaction_list/fin_num")
    Object retrieveHistory(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam @SpringQueryMap TransactionHistoryRequestDto dto
    );
}
