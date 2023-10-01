package wanted.pre_assignment.dto.response;

import lombok.*;

@Getter
@EqualsAndHashCode
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
