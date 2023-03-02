package site.project.accountinfoapp;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.httpclient.HttpClientConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@Generated
@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class, HttpClientConfiguration.class})
public class AccountInfoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountInfoAppApplication.class, args);
    }
}
