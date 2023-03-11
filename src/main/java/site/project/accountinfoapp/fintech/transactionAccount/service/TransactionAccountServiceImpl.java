package site.project.accountinfoapp.fintech.transactionAccount.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.common.component.ParamsGenerator;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiRequestDto.RegisteredInfoRequestDto;
import site.project.accountinfoapp.fintech.transactionAccount.dto.AccountApiRequestDto.TransactionHistoryRequestDto;
import site.project.accountinfoapp.fintech.transactionAccount.feignClient.AccountOpenFeign;
import site.project.accountinfoapp.service.kftcAuth.domain.KftcAuthorization;
import site.project.accountinfoapp.service.kftcAuth.repository.AuthorizationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionAccountServiceImpl implements TransactionAccountService {

    private final AccountOpenFeign openFeign;
    private final ParamsGenerator generator;
    private final AuthorizationRepository authorizationRepository;

    @Override
    public Object findOne(String finNum){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        KftcAuthorization auth = authorizationRepository.findByUsername(username).orElseThrow();
        log.info("TOKEN : "+ auth.getCntrToken());
        return openFeign.retrieveUserInfo(
                "Bearer "+auth.getCntrToken(),
                generator.bankTranId(),
                finNum,
                generator.tranTime());
    }

    @Override
    public Object findAll(String token, RegisteredInfoRequestDto dto) {

        return openFeign.retrieveRegisteredAccount(token, dto);
    }

    @Override
    public Object findHistory(String finNum, String fromDate, String toDate) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        KftcAuthorization auth = authorizationRepository.findByUsername(username).orElseThrow();

        return openFeign.retrieveHistory(
                "Bearer "+auth.getCntrToken(),
                generator.bankTranId(),
                finNum,
                'A',
                'D',
                fromDate,
                toDate,
                'D',
                generator.tranTime()
        );
    }

    private String setDefaultValue(String date) {
        if (Objects.equals(date, ""))
            date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return date;
    }
}
