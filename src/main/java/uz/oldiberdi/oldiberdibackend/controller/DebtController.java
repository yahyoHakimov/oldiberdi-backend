package uz.oldiberdi.oldiberdibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oldiberdi.oldiberdibackend.dto.DebtRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Debt;
import uz.oldiberdi.oldiberdibackend.service.DebtService;

import java.util.List;

@RestController
@RequestMapping("/api/debts")
@RequiredArgsConstructor
public class DebtController {

    private final DebtService debtService;

    // For now, mock the lender ID (in real use: from auth)
    private final Long MOCK_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<Debt> createDebt(@Valid @RequestBody DebtRequestDto dto) {
        return ResponseEntity.ok(debtService.createDebt(MOCK_USER_ID, dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Debt>> getMyDebts() {
        return ResponseEntity.ok(debtService.getMyDebts(MOCK_USER_ID));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Debt> markAsPaid(@PathVariable Long id) {
        return ResponseEntity.ok(debtService.markAsPaid(id, MOCK_USER_ID));
    }
}

