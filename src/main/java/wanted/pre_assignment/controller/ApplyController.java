package wanted.pre_assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wanted.pre_assignment.domain.User;
import wanted.pre_assignment.dto.request.ApplyRequest;
import wanted.pre_assignment.service.ApplyService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping(value = "/apply")
    public void userApply(@RequestBody ApplyRequest request) {
        applyService.apply(request);
    }
}
