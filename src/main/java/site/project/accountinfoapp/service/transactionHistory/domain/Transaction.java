package site.project.accountinfoapp.service.transactionHistory.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "transaction", indexes = {
        @Index(name = "index_tran_date", columnList = "tran_date")
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branchName;

    private String inoutType;

    private String printContent;

    private String tranAmt;

    @Column(name = "tran_date")
    private String tranDate;

    private String tranTime;

    private String toAccount;

    private String fromAccount;

    @Builder
    public Transaction(String branchName, String inoutType, String printContent, String tranAmt, String tranDate, String tranTime, String toAccount, String fromAccount) {
        this.branchName = branchName;
        this.inoutType = inoutType;
        this.printContent = printContent;
        this.tranAmt = tranAmt;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
    }
}