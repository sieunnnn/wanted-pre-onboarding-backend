package wanted.pre_assignment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_assignment.domain.JobPosting;
import wanted.pre_assignment.domain.User;
import wanted.pre_assignment.dto.request.ApplyRequest;
import wanted.pre_assignment.repository.JobPostingRepository;
import wanted.pre_assignment.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;

    @Transactional
    public void apply(ApplyRequest request) {
        // 유저 존재 여부 확인
        Optional<User> user = userRepository.findUserById(request.getUserId());
        if (user.isEmpty()) {
                throw  new EntityNotFoundException("찾는 유저가 존재하지 않습니다. 유저 아이디 :  " + request.getUserId());
        }

        // 이미 지원한 유저 인지 확인
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(request.getJobPostingId())
                .orElseThrow(() -> new EntityNotFoundException("찾는 공고가 존재하지 않습니다. 공고 아이디 :  " + request.getJobPostingId()));

        List<User> users = jobPosting.getUsers();
        for (User applyUser : users) {
            if (user.get().equals(applyUser.getId())) {
                throw new IllegalStateException("이미 지원한 공고입니다.");
            }
        }

        user.get().updateUserJobPosting(jobPosting);
    }
}
