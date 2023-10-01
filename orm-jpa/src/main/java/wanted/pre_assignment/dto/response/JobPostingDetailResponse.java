package wanted.pre_assignment.dto.response;

import lombok.*;

import java.util.List;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDetailResponse {
    private Long jobPostingId;
    private String companyName;
    private String national;
    private String region;
    private String recruitmentPosition;
    private Long recruitmentBonus;
    private String stack;
    private String recruitmentDetails;
    private List<Long> otherJobPostings;
}
