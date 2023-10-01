package wanted.pre_assignment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "job_posting_id")
    @JsonIgnore
    private JobPosting jobPosting;

    public void mappingJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
        jobPosting.mappingUser(this);
    }

    public void updateUserJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
        jobPosting.getUsers().add(this);
    }
}
