package vn.edu.iuh.fit.lab_5.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillLevel;
import vn.edu.iuh.fit.lab_5.backend.ids.CandidateSkillId;

@Getter
@Setter
@Entity
@Table(name = "candidate_skill", schema = "works")
@RequiredArgsConstructor
@NoArgsConstructor
public class CandidateSkill {
    @EmbeddedId
    @NonNull
    private CandidateSkillId id;

    @Column(name = "more_infos", length = 1000)
    private String moreInfos;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "skill_level", nullable = false)
    @NonNull
    private SkillLevel skillLevel;

    @ManyToOne
    @JoinColumn(name = "can_id", insertable = false, updatable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    private Skill skill;
}
