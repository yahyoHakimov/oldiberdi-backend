package uz.oldiberdi.oldiberdibackend.service;

import uz.oldiberdi.oldiberdibackend.dto.DebtRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Debt;

import java.util.List;

public interface DebtService {
    Debt createDebt(Long lenderId, DebtRequestDto dto);
    List<Debt> getMyDebts(Long userId);
    Debt markAsPaid(Long debtId, Long userId);
}
