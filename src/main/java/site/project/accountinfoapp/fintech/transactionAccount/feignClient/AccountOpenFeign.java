package site.project.accountinfoapp.fintech.transactionAccount.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiRequestDto.RegisteredInfoRequestDto;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiResponseDto.AccountInfoResponseDto;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiResponseDto.RegisteredInfoResponseDto;

@FeignClient(name = "Account", url = "https://testapi.openbanking.or.kr/")
public interface AccountOpenFeign {

    @GetMapping("/v2.0/account/balance/fin_num")
    AccountInfoResponseDto retrieveUserInfo(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam String bank_tran_id,
            @RequestParam String fintech_use_num,
            @RequestParam String tran_dtime
            );

    @GetMapping("/v2.0/account/list")
    RegisteredInfoResponseDto retrieveRegisteredAccount(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam @SpringQueryMap RegisteredInfoRequestDto dto
    );

    @GetMapping("/v2.0/account/transaction_list/fin_num")
    Object retrieveHistory(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam String bank_tran_id,
            @RequestParam String fintech_use_num,
            @RequestParam Character inquiry_type,
            @RequestParam Character inquiry_base,
            @RequestParam String from_date,
            @RequestParam String to_date,
            @RequestParam Character sort_order,
            @RequestParam String tran_dtime

    );
}
