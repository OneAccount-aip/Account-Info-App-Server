package site.project.accountinfoapp.service.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.project.accountinfoapp.service.account.domain.Account;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
