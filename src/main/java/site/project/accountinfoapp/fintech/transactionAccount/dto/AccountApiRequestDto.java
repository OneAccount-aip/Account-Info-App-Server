package site.project.accountinfoapp.fintech.transactionAccount.dto;

public record AccountApiRequestDto() {

    public record AccountInfoRequestDto(
            String bank_tran_id,
            String fintech_use_num,
            String tran_dtime
    ) {
    }

    public record RegisteredInfoRequestDto(
            String user_seq_no,
            Character include_cancel_yn,
            Character sort_order
    ) {
    }

    public record TransactionHistoryRequestDto(
            String bank_tran_id,
            String fintech_use_num,
            Character inquiry_type,
            Character inquiry_base,
            String from_date,
            String to_date,
            Character sort_order,
            String tran_dtime
    ) {
    }
}
