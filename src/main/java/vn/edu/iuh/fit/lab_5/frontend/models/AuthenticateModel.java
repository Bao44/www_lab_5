package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.dto.CandidateAccountDTO;
import vn.edu.iuh.fit.lab_5.backend.models.Address;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Response;;

import java.net.URI;

@Component
public class AuthenticateModel {

    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/";

    public Candidate checkLogin(String email, String passowrd) {
        Candidate candidate = null;
        CandidateAccountDTO dto = new CandidateAccountDTO(email, passowrd);
        Response response = rt.postForObject(URI.create(uri + "candidate/login"), dto, Response.class);
        candidate = mapper.convertValue(response.getData(), new TypeReference<>() {
        });
        return candidate;
    }

    public Address registerAddress(Address address) {
        Response responseAdd = rt.postForObject(URI.create(uri + "address"), address, Response.class);
        return mapper.convertValue(responseAdd.getData(), Address.class);
    }

    public boolean signUp(Candidate candidate) {
        Response response = rt.postForObject(URI.create(uri + "candidate"), candidate, Response.class);
        return response.getCode() == 200;
    }
}
