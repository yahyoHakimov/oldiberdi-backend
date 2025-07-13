package uz.oldiberdi.oldiberdibackend.service;

import uz.oldiberdi.oldiberdibackend.dto.GroupExpenseRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.GroupExpense;

public interface GroupExpenseService {
    GroupExpense createGroupExpense(Long groupId, GroupExpenseRequestDto dto);
}
