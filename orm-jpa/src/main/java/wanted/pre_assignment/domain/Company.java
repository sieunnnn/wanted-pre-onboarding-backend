package wanted.pre_assignment.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<JobPosting> jobPostings = new ArrayList<>();

    public void mappingJobPosting (JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }
}
