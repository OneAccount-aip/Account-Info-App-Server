package site.project.accountinfoapp.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static site.project.accountinfoapp.configuration.AppProperties.TranId;

@Component
public record ParamsGenerator(TranId tranId) {

    public String bankTranId() {
        return tranId.getHeader_string() + randomNumber();
    }

    private int randomNumber() {
        Random random = new Random();
        return random.nextInt(899999999) + 100000000;
    }

    public String tranTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
    }
}
