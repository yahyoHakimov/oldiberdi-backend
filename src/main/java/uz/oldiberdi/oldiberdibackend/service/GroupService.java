package uz.oldiberdi.oldiberdibackend.service;

import uz.oldiberdi.oldiberdibackend.dto.GroupRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Group;

import java.util.List;

public interface GroupService {
    Group createGroup(Long ownerId, GroupRequestDto groupRequestDto);
    List<Group> getMyGroups(Long userId);
}
