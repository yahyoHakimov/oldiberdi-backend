package uz.oldiberdi.oldiberdibackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_expenses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal totalAmount;

    @ManyToOne
    private User payer;

    @ManyToOne
    private Group group;

    private LocalDateTime createdAt =  LocalDateTime.now();

    @ManyToMany
    @JoinTable(name = "expense_participants",
        joinColumns = @JoinColumn(name = "expense_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> participants = new HashSet<>();



}
