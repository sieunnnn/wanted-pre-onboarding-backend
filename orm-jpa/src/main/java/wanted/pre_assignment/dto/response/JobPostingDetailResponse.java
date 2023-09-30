package wanted.pre_assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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
