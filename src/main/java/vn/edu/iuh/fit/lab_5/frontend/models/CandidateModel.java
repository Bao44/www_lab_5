package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.models.*;

import java.net.URI;
import java.util.List;

@Component
public class CandidateModel {

    private RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/candidate/";
    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public List<Candidate> getCandidatesForPage(int page) {
        Response response = rt.getForObject(URI.create(uri + "page/" + page), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<>() {
        });
    }

    public Candidate getCandidateDetail(Long id) {
        Response response = rt.getForObject(URI.create(uri + id), Response.class);
        return mapper.convertValue(response.getData(), Candidate.class);
    }

    public List<CandidateSkill> getCandidateSkill(Long id) {
        Response response = rt.getForObject(URI.create(uri + id + "/skills"), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<CandidateSkill>>() {});
    }

    public List<Experience> getCandidateExperiences(Long id) {
        Response response = rt.getForObject(URI.create(uri + id + "/experiences"), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Experience>>() {});
    }
}