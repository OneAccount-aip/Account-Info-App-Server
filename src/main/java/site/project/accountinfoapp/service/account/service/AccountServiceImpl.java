package site.project.accountinfoapp.service.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.service.account.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public Object findAll() {
        return accountRepository.findAll();
    }
}
