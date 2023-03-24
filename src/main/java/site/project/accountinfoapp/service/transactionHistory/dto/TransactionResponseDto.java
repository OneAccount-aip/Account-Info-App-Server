package site.project.accountinfoapp.service.transactionHistory.dto;

public record TransactionResponseDto(
){
    public record TransferResponseDto(
        String tran_date,
        String print_content,
        String tran_time,
        String inout_type,
        String tran_amt,
        String after_balance_amt
    ){
    }
}
