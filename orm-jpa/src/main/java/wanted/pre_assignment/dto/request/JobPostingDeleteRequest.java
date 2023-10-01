package wanted.pre_assignment.dto.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDeleteRequest {
    private Long jobPostingId;
}
