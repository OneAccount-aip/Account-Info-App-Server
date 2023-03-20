package site.project.accountinfoapp.test.bankAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.project.accountinfoapp.test.bankAccount.domain.BankAccount;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountNum(String accountNum);
    Optional<BankAccount> findByFintechUseNum(String finNum);
    List<BankAccount> findByAccountNumStartsWith(String accountNum);
}
