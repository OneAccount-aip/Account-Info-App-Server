package site.project.accountinfoapp.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "User", url = "https://testapi.openbanking.or.kr/")
public interface UserInfoOpenFeign {

    @GetMapping("/v2.0/user/me")
    Object retrieveUserInfo(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam("user_seq_no") String seqNo);
}
