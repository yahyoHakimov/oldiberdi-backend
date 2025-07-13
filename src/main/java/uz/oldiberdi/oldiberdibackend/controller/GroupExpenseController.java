package uz.oldiberdi.oldiberdibackend.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.oldiberdi.oldiberdibackend.dto.GroupExpenseRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.GroupExpense;
import uz.oldiberdi.oldiberdibackend.service.GroupExpenseService;

@Controller
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupExpenseController {
    private final GroupExpenseService groupExpenseService;

    @PostMapping("/{groupId}/expenses")
    public ResponseEntity<GroupExpense> createExpense(@PathVariable Long groupId,
                                                      @Valid @RequestBody GroupExpenseRequestDto dto) {
        return  ResponseEntity.ok(groupExpenseService.createGroupExpense(groupId, dto));
    }
}
