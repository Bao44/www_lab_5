package vn.edu.iuh.fit.lab_5.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.iuh.fit.lab_5.backend.enums.CandidateRole;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String email;
    protected String password;
    protected CandidateRole role;

    public User(Long id, String email, String password, CandidateRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
