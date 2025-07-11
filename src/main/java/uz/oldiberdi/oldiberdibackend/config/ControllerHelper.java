package uz.oldiberdi.oldiberdibackend.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.oldiberdi.oldiberdibackend.entity.User;

@Component
public class ControllerHelper {

    public Long getCurrentUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
