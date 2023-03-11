package site.project.accountinfoapp.fintech.transfer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.common.component.ParamsGenerator;
import site.project.accountinfoapp.common.component.AppProperties.Account;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.WithDrawRequestDto;
import site.project.accountinfoapp.fintech.transfer.feignClient.TransferOpenFeign;
import site.project.accountinfoapp.service.kftcAuth.repository.AuthorizationRepository;

import static site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{

    private final AuthorizationRepository authorizationRepository;
    private final TransferOpenFeign openFeign;
    private final ParamsGenerator generator;
    private final Account account;

    @Override
    public Object withDraw(WithDrawRequestDto dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var auth = authorizationRepository.findByUsername(username).orElseThrow();

        dto.setBank_tran_id(generator.bankTranId());
        dto.setTran_dtime(generator.tranTime());
        dto.setCntr_account_type('N');
        dto.setCntr_account_num("973649318747");
        dto.setReq_client_num('R');
        dto.setTransfer_purpose("TR");
        dto.setRecv_client_name(account.getRecv_client_name());
        dto.setRecv_client_bank_code(account.getRecv_client_bank_code());
        dto.setRecv_client_account_num(account.getRecv_client_account_num());
        dto.setSub_frnc_name(account.getSub_frnc_name());
        dto.setSub_frnc_num(account.getSub_frnc_num());
        dto.setSub_frnc_business_num(account.getSub_frnc_business_num());

        return openFeign.withdraw("Bearer "+auth.getCntrToken(), dto);
    }

    @Override
    public Object deposit(DepositRequestDto dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var auth = authorizationRepository.findByUsername(username).orElseThrow();

        dto.setReq_cnt(1);
        dto.setCntr_account_type('N');
        dto.setCntr_account_num("973649318747");
        dto.setWd_pass_phrase("NONE");
        dto.setName_check_option("on");
        dto.setTran_dtime(generator.tranTime());
        dto.getReq_list().get(0).setTran_no(generator.transactionId(5));
        dto.getReq_list().get(0).setBank_tran_id(generator.bankTranId());

        return openFeign.deposit("Bearer "+auth.getDepositToken(), dto);
    }
}
