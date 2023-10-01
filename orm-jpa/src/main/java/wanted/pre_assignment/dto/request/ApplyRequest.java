package wanted.pre_assignment.dto.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyRequest {
    private Long jobPostingId;
    private Long userId;
}
