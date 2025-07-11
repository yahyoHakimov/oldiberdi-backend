package uz.oldiberdi.oldiberdibackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.oldiberdi.oldiberdibackend.dto.DebtRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Debt;
import uz.oldiberdi.oldiberdibackend.entity.DebtStatus;
import uz.oldiberdi.oldiberdibackend.entity.User;
import uz.oldiberdi.oldiberdibackend.repository.DebtRepository;
import uz.oldiberdi.oldiberdibackend.repository.UserRepository;
import uz.oldiberdi.oldiberdibackend.service.DebtService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;
    private final UserRepository userRepository;

    @Override
    public Debt createDebt(Long lenderId, DebtRequestDto dto) {
        User lender = userRepository.findById(lenderId)
                .orElseThrow(() -> new RuntimeException("Lender not found"));

        User borrower = userRepository.findById(dto.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower not found"));

        Debt debt = Debt.builder()
                .lender(lender)
                .borrower(borrower)
                .amount(dto.getAmount())
                .dueDate(dto.getDueDate())
                .build();

        return debtRepository.save(debt);
    }

    @Override
    public List<Debt> getMyDebts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return debtRepository.findAllByLenderOrBorrower(user, user);
    }

    @Override
    public Debt markAsPaid(Long debtId, Long userId) {
        Debt debt = debtRepository.findById(debtId)
                .orElseThrow(() -> new RuntimeException("Debt not found"));

        if (!debt.getLender().getId().equals(userId) &&
                !debt.getBorrower().getId().equals(userId)) {
            throw new RuntimeException("Not authorized to update this debt");
        }

        debt.setStatus(DebtStatus.PAID);
        return debtRepository.save(debt);
    }
}
