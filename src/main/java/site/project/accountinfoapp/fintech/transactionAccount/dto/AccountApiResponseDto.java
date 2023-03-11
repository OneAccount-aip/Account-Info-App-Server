package site.project.accountinfoapp.fintech.transactionAccount.dto;

import lombok.Data;

import java.util.List;

public record AccountApiResponseDto() {

    @Data
    public static class AccountInfoResponseDto {
        String api_tran_id;
        String rsp_code;
        String rsp_message;
        String api_tran_dtm;
        String bank_tran_id;
        String bank_tran_date;
        String bank_code_tran;
        String bank_rsp_code;
        String bank_rsp_message;
        String fintech_use_num;
        String balance_amt;
        String available_amt;
        String account_type;
        String product_name;
        String bank_name;
        String savings_bank_name;
        String account_issue_date;
        String maturity_date;
        String last_tran_date;
    }

    @Data
    public static class RegisteredInfoResponseDto {
        String api_tran_id;
        String rsp_code;
        String rsp_message;
        String api_tran_dtm;
        String user_name;
        Integer res_cnt;
        List<Registered_list> res_list;
    }

    @Data
    private static class Registered_list {
        String fintech_use_num;
        String account_alias;
        String bank_code_std;
        String bank_code_sub;
        String bank_name;
        String account_num_masked;
        String account_holder_name;
        Character account_holder_type;
        Character inquiry_agree_yn;
        String inquiry_agree_dtime;
        Character transfer_agree_yn;
        String transfer_agree_dtime;
        String account_state;
        String savings_bank_name;
        String account_seq;
        Integer account_type;
    }

    @Data
    public static class TransactionHistoryResponseDto {
        String api_tran_id;
        String rsp_code;
        String rsp_message;
        String api_tran_dtm;
        String bank_tran_id;
        String bank_tran_date;
        String bank_code_tran;
        String bank_rsp_code;
        String bank_rsp_message;
        String fintech_use_num;
        Integer balance_amt;
        Integer page_record_cnt;
        Character next_page_yn;
        String befor_inquiry_trace_info;
        String bank_name;
        String savings_bank_name;
        List<TransactionHistory_list> res_list;
    }

    @Data
    private static class TransactionHistory_list {
        String tran_date;
        String tran_time;
        String inout_type;
        String tran_type;
        String print_content;
        Integer tran_amt;
        String after_balance_amt;
        String branch_name;
    }
}
