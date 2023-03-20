package site.project.accountinfoapp.service.transactionHistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.project.accountinfoapp.service.transactionHistory.domain.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccount(String finNum);
}
