package site.project.accountinfoapp.fintech.transfer.service;

import static site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.DepositRequestDto;
import static site.project.accountinfoapp.fintech.transfer.dto.TransferRequestDto.WithDrawRequestDto;

public interface TransferService {

    Object withDraw(WithDrawRequestDto dto);
    Object deposit(DepositRequestDto dto);
}
