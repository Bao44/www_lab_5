package vn.edu.iuh.fit.lab_5.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class JobDTO {
    private Long jobId;
    private String jobName;
    private String jobDesc;
    private Long matchingSkills;
    private Double matchingPercentage;
}
