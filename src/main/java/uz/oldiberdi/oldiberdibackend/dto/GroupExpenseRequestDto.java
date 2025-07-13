package uz.oldiberdi.oldiberdibackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter @Setter
public class GroupExpenseRequestDto {
    @NotNull
    private Long payerId;
    @NotNull
    private BigDecimal totalAmount;
    private String description;

    @NotEmpty
    private List<Long> participantIds;

}
