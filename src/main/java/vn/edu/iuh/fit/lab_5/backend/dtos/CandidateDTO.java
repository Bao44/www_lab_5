package vn.edu.iuh.fit.lab_5.backend.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class CandidateDTO implements Serializable {
    Long id;
    LocalDate dob;
    String email;
    String fullName;
    String phone;
    AddressDTO address;
}