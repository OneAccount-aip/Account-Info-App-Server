package site.project.accountinfoapp.fintech.transfer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.project.accountinfoapp.common.component.AppProperties.Account;
import site.project.accountinfoapp.common.component.ParamsGenerator;
import site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.WithDrawRequestDto;
import site.project.accountinfoapp.fintech.transfer.feignClient.TransferOpenFeign;
import site.project.accountinfoapp.service.kftcAuth.repository.AuthorizationRepository;

import static site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.DepositRequestDto;

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

        dto = WithDrawRequestDto.builder()
                .bank_tran_id(generator.bankTranId())
                .tran_dtime(generator.tranTime())
                .cntr_account_type('N')
                .cntr_account_num("973649318747")
                .req_client_num('R')
                .transfer_purpose("TR")
                .recv_client_name(account.getRecv_client_name())
                .recv_client_bank_code(account.getRecv_client_bank_code())
                .recv_client_account_num(account.getRecv_client_account_num())
                .sub_frnc_name(account.getSub_frnc_name())
                .sub_frnc_num(account.getSub_frnc_num())
                .sub_frnc_business_num(account.getSub_frnc_business_num())
                .build();

        return openFeign.withdraw("Bearer "+auth.getCntrToken(), dto);
    }

    @Override
    public Object deposit(DepositRequestDto dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var auth = authorizationRepository.findByUsername(username).orElseThrow();

        dto = DepositRequestDto.builder()
                .req_cnt(1)
                .cntr_account_type('N')
                .cntr_account_num("973649318747")
                .wd_pass_phrase("NONE")
                .name_check_option("on")
                .tran_dtime(generator.tranTime())
                .build();

        dto.getReq_list().get(0).setTran_no(generator.transactionId(5));
        dto.getReq_list().get(0).setBank_tran_id(generator.bankTranId());

        return openFeign.deposit("Bearer "+auth.getDepositToken(), dto);
    }
}
