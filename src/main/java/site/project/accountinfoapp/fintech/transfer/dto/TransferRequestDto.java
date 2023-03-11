package site.project.accountinfoapp.fintech.transfer.dto;

import lombok.Data;

import java.util.List;

public record TransferRequestDto(
) {
    @Data
    public static class WithDrawRequestDto{
            String bank_tran_id;
            Character cntr_account_type;
            String cntr_account_num;
            String dps_print_content;
            String fintech_use_num;
            String wd_print_content;
            String tran_amt;
            String tran_dtime;
            String req_client_name;
            String req_client_bank_code;
            String req_client_account_num;
            Character req_client_num;
            String transfer_purpose;
            String sub_frnc_name;
            String sub_frnc_num;
            String sub_frnc_business_num;
            String recv_client_name;
            String recv_client_bank_code;
            String recv_client_account_num;
    }

    @Data
    public static class DepositRequestDto{
            Character cntr_account_type;
            String cntr_account_num;
            String wd_pass_phrase;
            String wd_print_content;
            String name_check_option;
            String tran_dtime;
            Integer req_cnt;
            List<Deposit_list> req_list;
    }

    @Data
    public static class Deposit_list{
            String tran_no;
            String bank_tran_id;
            String fintech_use_num;
            String print_content;
            Integer tran_amt;
            Integer req_client_bank_code;
            String req_client_account_num;
            String req_client_fintech_use;
            String req_client_name;
            String req_client_num;
            String transfer_purpose;
    }
}
