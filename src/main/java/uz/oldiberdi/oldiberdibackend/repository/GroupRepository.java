package uz.oldiberdi.oldiberdibackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oldiberdi.oldiberdibackend.entity.Group;
import uz.oldiberdi.oldiberdibackend.entity.User;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByOwner(User user);
}
