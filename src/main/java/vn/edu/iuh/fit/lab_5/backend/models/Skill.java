package vn.edu.iuh.fit.lab_5.backend.models;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.lab_5.backend.enums.SkillType;

@Getter
@Setter
@Entity
@Table(name = "skill", schema = "works")
@RequiredArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @Column(name = "skill_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_description")
    private String skillDescription;

    @Column(name = "skill_name")
    @NonNull
    private String skillName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    @NonNull
    private SkillType type;

    public Skill(String skillDescription, @NonNull String skillName, @NonNull SkillType type) {
        this.skillDescription = skillDescription;
        this.skillName = skillName;
        this.type = type;
    }

    public Skill(Long id, String skillDescription, @NonNull String skillName, @NonNull SkillType type) {
        this.id = id;
        this.skillDescription = skillDescription;
        this.skillName = skillName;
        this.type = type;
    }

    public Skill(@NonNull String skillName) {
        this.skillName = skillName;
    }
}