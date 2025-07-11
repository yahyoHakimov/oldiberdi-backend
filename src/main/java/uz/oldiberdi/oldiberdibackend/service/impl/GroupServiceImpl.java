package uz.oldiberdi.oldiberdibackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.oldiberdi.oldiberdibackend.dto.GroupRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Group;
import uz.oldiberdi.oldiberdibackend.entity.User;
import uz.oldiberdi.oldiberdibackend.repository.GroupRepository;
import uz.oldiberdi.oldiberdibackend.repository.UserRepository;
import uz.oldiberdi.oldiberdibackend.service.GroupService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public Group createGroup(Long ownerId, GroupRequestDto groupRequestDto) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Set<User> members = groupRequestDto.getMemberPhones().stream()
                .map(phoneNumber -> userRepository.findByPhoneNumber(phoneNumber)
                        .orElseThrow(() -> new RuntimeException("User not found: " + phoneNumber)))
                .collect(Collectors.toSet());

        Group group = Group.builder()
                .name(groupRequestDto.getName())
                .owner(owner)
                .members(members)
                .build();

        return groupRepository.save(group);
    }

    @Override
    public List<Group> getMyGroups(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        return groupRepository.findByOwner(user);
    }
}
