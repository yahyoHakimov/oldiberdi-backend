package uz.oldiberdi.oldiberdibackend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.oldiberdi.oldiberdibackend.dto.GroupExpenseRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.*;
import uz.oldiberdi.oldiberdibackend.repository.DebtRepository;
import uz.oldiberdi.oldiberdibackend.repository.GroupExpenseRepository;
import uz.oldiberdi.oldiberdibackend.repository.GroupRepository;
import uz.oldiberdi.oldiberdibackend.repository.UserRepository;
import uz.oldiberdi.oldiberdibackend.service.GroupExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupExpenseServiceImpl implements GroupExpenseService {
    private final GroupExpenseRepository groupExpenseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final DebtRepository debtRepository;

    @Override
    public GroupExpense createGroupExpense(Long groupId, GroupExpenseRequestDto dto) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User payer = userRepository.findById(dto.getPayerId())
                .orElseThrow(() -> new RuntimeException("Payer not found"));

        Set<User> participants = dto.getParticipantIds().stream()
                .map(id -> userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found: " + id)))
                .collect(Collectors.toSet());

        GroupExpense expense = GroupExpense.builder()
                .group(group)
                .payer(payer)
                .description(dto.getDescription())
                .totalAmount(dto.getTotalAmount())
                .participants(participants)
                .build();

        GroupExpense saved = groupExpenseRepository.save(expense);


        // Calculate share per participant
        BigDecimal share = dto.getTotalAmount()
                .divide(BigDecimal.valueOf(participants.size()), 2, BigDecimal.ROUND_HALF_UP);

        // Create debts (only if participant ≠ payer)
        for (User participant : participants) {
            if (!participant.getId().equals(payer.getId())) {
                Debt debt = Debt.builder()
                        .lender(payer)
                        .borrower(participant)
                        .amount(share)
                        .dueDate(LocalDate.now().plusDays(7))
                        .status(DebtStatus.PENDING)
                        .build();
                debtRepository.save(debt);

            }

        }
        return saved;

    }
}
