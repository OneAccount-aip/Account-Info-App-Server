package site.project.accountinfoapp.service.kftcAuth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class KftcAuthorization {

    @Column @Id
    private String username;

    @Column @Length(max = 1000)
    private String cntrToken;

    @Column @Length(max = 1000)
    private String depositToken;

    @Column @Length(max = 1000)
    private String refreshToken;

    @Column @Length(max = 255)
    private String userSeqNo;

    @Column @Length(max = 255)
    private String clientUseCode;

    @Builder
    public KftcAuthorization(String username, String cntrToken, String depositToken, String refreshToken, String userSeqNo, String clientUseCode) {
        this.username = username;
        this.cntrToken = cntrToken;
        this.depositToken = depositToken;
        this.refreshToken = refreshToken;
        this.userSeqNo = userSeqNo;
        this.clientUseCode = clientUseCode;
    }

    public void update(String cntrToken, String depositToken, String refreshToken, String userSeqNo, String clientUseCode){
        this.cntrToken = cntrToken;
        this.depositToken = depositToken;
        this.refreshToken = refreshToken;
        this.userSeqNo = userSeqNo;
        this.clientUseCode = clientUseCode;
    }
}
