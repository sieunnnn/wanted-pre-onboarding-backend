package wanted.pre_assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class JobPostingRequest {
    private String national;
    private String region;
    private String recruitmentPosition;
    private Long recruitmentBonus;
    private String stack;
    private String recruitmentDetails;
}
