package site.project.accountinfoapp.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.account.dto.AccountRequestDto.AccountInfoRequestDto;
import site.project.accountinfoapp.account.dto.AccountRequestDto.RegisteredInfoRequestDto;
import site.project.accountinfoapp.account.dto.AccountRequestDto.TransactionHistoryRequestDto;
import site.project.accountinfoapp.api.UserAccountOpenFeign;
import site.project.accountinfoapp.common.ParamsGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserAccountOpenFeign openFeign;
    private final ParamsGenerator generator;

    @Override
    public Object findOne(String token, String finNum) {

        return openFeign.retrieveUserInfo(token, new AccountInfoRequestDto(
                generator.bankTranId(),
                finNum,
                generator.tranTime()));
    }

    @Override
    public Object findAll(String token, RegisteredInfoRequestDto dto) {

        return openFeign.retrieveRegisteredAccount(token, dto);
    }

    @Override
    public Object findHistory(String token, String finNum, String fromDate, String toDate) {

        return openFeign.retrieveHistory(token, new TransactionHistoryRequestDto(
                generator.bankTranId(),
                finNum,
                'A',
                'D',
                setDefaultValue(fromDate),
                setDefaultValue(toDate),
                'D',
                generator.tranTime()
        ));
    }

    private String setDefaultValue(String date) {
        if (Objects.equals(date, ""))
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return date;
    }
}
