package uz.oldiberdi.oldiberdibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oldiberdi.oldiberdibackend.entity.Debt;
import uz.oldiberdi.oldiberdibackend.entity.User;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt,Long> {
    List<Debt> findAllByLenderOrBorrower(User lender, User borrower);
}
