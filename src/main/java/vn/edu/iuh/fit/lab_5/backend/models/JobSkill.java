package vn.edu.iuh.fit.lab_5.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.lab_5.backend.ids.JobSkillId;

@Getter
@Setter
@Entity
@Table(name = "job_skill", schema = "works")
@NoArgsConstructor
@RequiredArgsConstructor
public class JobSkill {
    @EmbeddedId @NonNull
    private JobSkillId id;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level", nullable = false) @NonNull
    private SkillLevel skillLevel;
}