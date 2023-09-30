package wanted.pre_assignment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_assignment.domain.Company;
import wanted.pre_assignment.domain.JobPosting;
import wanted.pre_assignment.dto.request.JobPostingDeleteRequest;
import wanted.pre_assignment.dto.request.JobPostingRequest;
import wanted.pre_assignment.dto.response.JobPostingDetailResponse;
import wanted.pre_assignment.dto.response.JobPostingResponse;
import wanted.pre_assignment.repository.CompanyRepository;
import wanted.pre_assignment.repository.JobPostingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public void createJobPosting(JobPostingRequest request, Long companyId) {
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("찾는 회사가 존재하지 않습니다. 회사 아이디 :  " + companyId));

        JobPosting jobPosting = JobPosting.builder()
                .company(company)
                .national(request.getNational())
                .region(request.getRegion())
                .recruitmentPosition(request.getRecruitmentPosition())
                .recruitmentBonus(request.getRecruitmentBonus())
                .stack(request.getStack())
                .recruitmentDetails(request.getRecruitmentDetails())
                .build();

        jobPostingRepository.save(jobPosting);
    }

    @Transactional
    public void updateJobPosting(JobPostingRequest request, Long jobPostingId, Long companyId) {
        Company company = companyRepository.findCompanyById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("찾는 회사가 존재하지 않습니다. 회사 아이디 :  " + companyId));
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(jobPostingId)
                .orElseThrow(() -> new EntityNotFoundException("찾는 공고가 존재하지 않습니다. 공고 아이디 : " + jobPostingId));
        jobPosting.updateJobPosting(request, company);
    }

    @Transactional
    public void deleteJobPosting(JobPostingDeleteRequest request) {
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(request.getJobPostingId())
                .orElseThrow(() -> new EntityNotFoundException("찾는 공고가 존재하지 않습니다. 공고 아이디 : " + request.getJobPostingId()));
        jobPostingRepository.delete(jobPosting);
    }

    public List<JobPostingResponse> getJobPostings () {

        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        List<JobPostingResponse> jobPostingResponses = new ArrayList<>();

        for (JobPosting jobPosting : jobPostings) {
            JobPostingResponse jobPostingResponse = JobPostingResponse.builder()
                    .jobPostingId(jobPosting.getId())
                    .companyName(jobPosting.getCompany().getName())
                    .national(jobPosting.getNational())
                    .region(jobPosting.getRegion())
                    .recruitmentPosition(jobPosting.getRecruitmentPosition())
                    .recruitmentBonus(jobPosting.getRecruitmentBonus())
                    .stack(jobPosting.getStack())
                    .build();
            jobPostingResponses.add(jobPostingResponse);
        }

        return jobPostingResponses;
    }

    public JobPostingDetailResponse getJobPostingsDetail (Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findJobPostingById(jobPostingId)
                .orElseThrow(() -> new EntityNotFoundException("찾는 공고가 존재하지 않습니다. 공고 아이디 : " + jobPostingId));
        List<Long> otherJobPostings = jobPostingRepository.findOtherJobPostingsByCompanyId(jobPosting.getCompany().getId(), jobPostingId);

        return JobPostingDetailResponse.builder()
                .jobPostingId(jobPosting.getId())
                .companyName(jobPosting.getCompany().getName())
                .national(jobPosting.getNational())
                .region(jobPosting.getRegion())
                .recruitmentPosition(jobPosting.getRecruitmentPosition())
                .recruitmentBonus(jobPosting.getRecruitmentBonus())
                .stack(jobPosting.getStack())
                .recruitmentDetails(jobPosting.getRecruitmentDetails())
                .otherJobPostings(otherJobPostings)
                .build();
    }
}
