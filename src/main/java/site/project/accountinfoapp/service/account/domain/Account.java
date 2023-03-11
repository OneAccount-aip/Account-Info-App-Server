package site.project.accountinfoapp.service.account.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private UUID userId;

    @Column
    private Long fintechUseNo;

    @Column
    private String alias;

    @Column
    private Integer backCode;

    @Column
    private String bankName;

    @Column
    private Long accountNum;

    @Column
    private String account_holder_name;
}
