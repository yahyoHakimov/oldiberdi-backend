package uz.oldiberdi.oldiberdibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oldiberdi.oldiberdibackend.config.ControllerHelper;
import uz.oldiberdi.oldiberdibackend.dto.DebtRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Debt;
import uz.oldiberdi.oldiberdibackend.service.DebtService;

import java.util.List;

@RestController
@RequestMapping("/api/debts")
@RequiredArgsConstructor
public class DebtController {

    private final DebtService debtService;
    private final ControllerHelper controllerHelper;

    @PostMapping
    public ResponseEntity<Debt> createDebt(@Valid @RequestBody DebtRequestDto dto) {
        Long userId = controllerHelper.getCurrentUserId();
        return ResponseEntity.ok(debtService.createDebt(userId, dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Debt>> getMyDebts() {
        Long userId = controllerHelper.getCurrentUserId();
        return ResponseEntity.ok(debtService.getMyDebts(userId));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Debt> markAsPaid(@PathVariable Long id) {
        Long userId = controllerHelper.getCurrentUserId();
        return ResponseEntity.ok(debtService.markAsPaid(id, userId));
    }



}

