package uz.oldiberdi.oldiberdibackend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DebtRequestDto {

    @NotNull
    private Long borrowerId;

    @NotNull
    @DecimalMin("1000")
    private BigDecimal amount;

    @Future
    private LocalDate dueDate;
}
