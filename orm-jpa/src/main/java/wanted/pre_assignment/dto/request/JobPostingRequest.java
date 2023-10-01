package wanted.pre_assignment.dto.request;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@EqualsAndHashCode
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
