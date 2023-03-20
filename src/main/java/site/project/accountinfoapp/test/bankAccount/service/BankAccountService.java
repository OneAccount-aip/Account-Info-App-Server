package site.project.accountinfoapp.test.bankAccount.service;

import site.project.accountinfoapp.test.bankAccount.domain.BankAccount;

import java.util.List;

public interface BankAccountService {
    List<BankAccount> retrieveContainingAccount(String number);

    Object retrieveAccountByFinNum(String number);
}
