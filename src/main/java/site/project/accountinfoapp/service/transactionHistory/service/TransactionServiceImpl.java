package site.project.accountinfoapp.service.transactionHistory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.service.transactionHistory.domain.Transaction;
import site.project.accountinfoapp.service.transactionHistory.dto.TransactionRequestDto.TransferRequestDto;
import site.project.accountinfoapp.service.transactionHistory.repository.TransactionRepository;
import site.project.accountinfoapp.test.bankAccount.repository.BankAccountRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public Object getHistory(String finNum){
        return transactionRepository.findByFromAccount(finNum);
    }

    @Override
    public Object transfer(TransferRequestDto dto) {

        LocalDateTime datetime = LocalDateTime.now();

        Transaction from = Transaction.builder()
                .branchName("송금")
                .inoutType("출금")
                .tranAmt(dto.amount())
                .printContent(dto.content())
                .tranDate(datetime.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .tranTime(datetime.format(DateTimeFormatter.ofPattern("HHmmss")))
                .fromAccount(dto.fromFinNum())
                .toAccount(bankAccountRepository.findByAccountNum(dto.toAccount()).get().getFintechUseNum())
                .build();

        Transaction to = Transaction.builder()
                .branchName("송금")
                .inoutType("입금")
                .tranAmt(dto.amount())
                .printContent(dto.content())
                .tranDate(datetime.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .tranTime(datetime.format(DateTimeFormatter.ofPattern("HHmmss")))
                .fromAccount(bankAccountRepository.findByAccountNum(dto.toAccount()).get().getFintechUseNum())
                .toAccount(dto.fromFinNum())
                .build();

        transactionRepository.save(from);
        transactionRepository.save(to);

        return null;
    }
}
