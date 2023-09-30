package wanted.pre_assignment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.pre_assignment.domain.JobPosting;
import wanted.pre_assignment.dto.request.JobPostingDeleteRequest;
import wanted.pre_assignment.dto.request.JobPostingRequest;
import wanted.pre_assignment.dto.response.JobPostingDetailResponse;
import wanted.pre_assignment.dto.response.JobPostingResponse;
import wanted.pre_assignment.service.JobPostingService;

import java.util.List;

@RestController
@RequestMapping(value = "job-posting")
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    // 리스트 조회
    @GetMapping(value = "")
    public ResponseEntity<List<JobPostingResponse>> getJobPostingList() {
        List<JobPostingResponse> jobPostingResponseList = jobPostingService.getJobPostings();
        return ResponseEntity.ok(jobPostingResponseList);
    }

    // 채용 공고 생성
    @PostMapping(value = "")
    public void createJobPosting(@RequestBody JobPostingRequest request, @RequestParam Long companyId) {
        jobPostingService.createJobPosting(request, companyId);
    }

    // 채용 공고 수정
    @PatchMapping(value = "")
    public void updateJobPosting(@RequestBody JobPostingRequest request, @RequestParam Long jobPostingId, @RequestParam Long companyId) {
        jobPostingService.updateJobPosting(request, jobPostingId, companyId);
    }

    // 채용 공고 삭제
    @DeleteMapping(value = "")
    public void deleteJobPosting(@RequestBody JobPostingDeleteRequest request) {
        jobPostingService.deleteJobPosting(request);
    }

    // 채용 공고 상세
    @GetMapping(value = "/detail")
    public ResponseEntity<JobPostingDetailResponse> getJobPostingDetail(@RequestParam Long jobPostingId) {
        JobPostingDetailResponse jobPostingDetailResponse = jobPostingService.getJobPostingsDetail(jobPostingId);
        return ResponseEntity.ok(jobPostingDetailResponse);
    }
}
