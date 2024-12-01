package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;

import java.net.URI;
import java.util.List;

@Component
public class AdminHomeModel {
    private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/";

    public List<Skill> getAllSkills() {
        List<Skill> result = null;

        Response response = rt.getForObject(URI.create(uri + "skill"), Response.class);
        result = mapper.convertValue(response.getData(), new TypeReference<List<Skill>>() {
        });

        return result;
    }

    public List<Candidate> getAllCandidates() {
        List<Candidate> result = null;

        Response response = rt.getForObject(URI.create(uri + "candidate"), Response.class);
        result = mapper.convertValue(response.getData(), new TypeReference<List<Candidate>>() {
        });

        return result;
    }

    public List<Job> getAllJobs() {
        List<Job> result = null;

        Response response = rt.getForObject(URI.create(uri + "job"), Response.class);
        result = mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
        });

        return result;
    }
}
