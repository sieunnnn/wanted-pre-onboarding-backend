package wanted.pre_assignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import wanted.pre_assignment.dto.request.JobPostingRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String national;
    private String region;
    private String recruitmentPosition;
    private Long recruitmentBonus;
    private String stack;
    private String recruitmentDetails;

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Builder.Default
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    public void mappingUser (User user) {
        users.add(user);
    }

    public void mappingCompany(Company company) {
        this.company = company;
        company.mappingJobPosting(this);
    }

    public void updateJobPosting(JobPostingRequest jobPostingRequest, Company company) {
        if (company != null) this.company = company;
        if (jobPostingRequest.getNational() != null) this.national = jobPostingRequest.getNational();
        if (jobPostingRequest.getRegion() != null) this.region = jobPostingRequest.getRegion();
        if (jobPostingRequest.getRecruitmentPosition() != null) this.recruitmentPosition = jobPostingRequest.getRecruitmentPosition();
        if (jobPostingRequest.getRecruitmentBonus() != null) this.recruitmentBonus = jobPostingRequest.getRecruitmentBonus();
        if (jobPostingRequest.getStack() != null) this.stack = jobPostingRequest.getStack();
        if (jobPostingRequest.getRecruitmentDetails() != null) this.recruitmentDetails = jobPostingRequest.getRecruitmentDetails();
    }
}
