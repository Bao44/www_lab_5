package vn.edu.iuh.fit.lab_5.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateAccountDTO {
    private String email;
    private String password;
}