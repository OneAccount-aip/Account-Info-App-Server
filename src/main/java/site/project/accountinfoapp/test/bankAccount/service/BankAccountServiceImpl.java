package site.project.accountinfoapp.test.bankAccount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.test.bankAccount.domain.BankAccount;
import site.project.accountinfoapp.test.bankAccount.repository.BankAccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> retrieveContainingAccount(String number) {
        return bankAccountRepository.findByAccountNumStartsWith(number);
    }

    @Override
    public Object retrieveAccountByFinNum(String number) {
        return bankAccountRepository.findByFintechUseNum(number).get();
    }
}
