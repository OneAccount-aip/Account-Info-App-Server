package site.project.accountinfoapp.fintech.transfer.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.DepositRequestDto;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.WithDrawRequestDto;

@FeignClient(name = "Transfer", url = "https://testapi.openbanking.or.kr/")
public interface TransferOpenFeign {

    @PostMapping(path = "/v2.0/transfer/withdraw/fin_num")
    Object withdraw(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody WithDrawRequestDto dto
    );

    @PostMapping(path = "/v2.0/transfer/deposit/fin_num")
    Object deposit(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody DepositRequestDto dto
    );
}
