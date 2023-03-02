package site.project.accountinfoapp.configuration;

import feign.Logger;
import feign.codec.Decoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("site.project.accountinfoapp")
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    Decoder feignDecoder(){
        return new DecodeConfig();
    }
}
