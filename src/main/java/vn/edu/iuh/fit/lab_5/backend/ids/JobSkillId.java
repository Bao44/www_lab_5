package vn.edu.iuh.fit.lab_5.backend.ids;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JobSkillId implements java.io.Serializable {
    private static final long serialVersionUID = 2323802082645217969L;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

}