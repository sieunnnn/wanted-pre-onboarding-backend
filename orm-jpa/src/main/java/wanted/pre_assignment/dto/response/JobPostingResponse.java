package wanted.pre_assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingResponse {
    private Long jobPostingId;
    private String companyName;
    private String national;
    private String region;
    private String recruitmentPosition;
    private Long recruitmentBonus;
    private String stack;
}
