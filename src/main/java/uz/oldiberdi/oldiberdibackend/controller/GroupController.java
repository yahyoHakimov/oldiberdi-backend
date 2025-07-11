package uz.oldiberdi.oldiberdibackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.oldiberdi.oldiberdibackend.dto.GroupRequestDto;
import uz.oldiberdi.oldiberdibackend.entity.Group;
import uz.oldiberdi.oldiberdibackend.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final Long MOCK_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<Group> createGroup(@Valid @RequestBody GroupRequestDto dto) {
        return ResponseEntity.ok(groupService.createGroup(MOCK_USER_ID, dto));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Group>> getMyGroups() {
        return ResponseEntity.ok(groupService.getMyGroups(MOCK_USER_ID));
    }
}
