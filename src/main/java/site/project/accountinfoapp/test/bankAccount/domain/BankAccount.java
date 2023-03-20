package site.project.accountinfoapp.test.bankAccount.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BankAccount {

    @Id
    @Column
    private String accountNum;

    @Column
    private String fintechUseNum;

    @Column
    private String bankCode;

    @Column
    private String username;
}
