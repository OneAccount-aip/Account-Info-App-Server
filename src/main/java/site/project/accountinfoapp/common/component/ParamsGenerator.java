package site.project.accountinfoapp.common.component;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static site.project.accountinfoapp.common.component.AppProperties.TranId;

@Component
public record ParamsGenerator(TranId tranId) {

    public String bankTranId() {
        return tranId.getHeader_string() + randomNumber(100000000);
    }

    public String transactionId(int length) {
        return String.valueOf(randomNumber(10000)%(10^(8-length)));
    }

    private int randomNumber(int startNum) {
        Random random = new Random();
        return random.nextInt(100000000) + startNum;
    }

    public String tranTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
    }
}

