package site.project.accountinfoapp.service.transactionHistory.dto;

public record TransactionRequestDto(
){
    public record TransferRequestDto(
        String fromFinNum,
        String toAccount,
        String amount,
        String content
    ){
    }
}
