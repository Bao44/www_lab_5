package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.CandidateSkill;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Response;

import java.net.URI;
import java.util.List;

@Component
public class JobSkillModel {

    private final RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/job-skill";
    private final ObjectMapper mapper = new ObjectMapper();

    public JobSkill addJobSkills(JobSkill jobSkills) {
        Response response = rt.postForObject(
                URI.create("http://localhost:8080/api/job-skill/add"),
                jobSkills,
                Response.class
        );
        return mapper.convertValue(response.getData(), JobSkill.class);
    }

    public List<JobSkill> getJobSkillForPage(int page) {
        Response response = rt.getForObject(URI.create(uri + "/page/" + page), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<>() {
        });
    }

    public List<JobSkill> getAllJobsBySkill(Long skillId) {
        Response response = rt.getForObject(URI.create(uri + "/jobs/" + skillId), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<JobSkill>>() {});
    }

    public List<JobSkill> getJobSkillsDetail(Long jobId) {
        Response response = rt.getForObject(URI.create(uri + "/skills/" + jobId), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<JobSkill>>() {});
    }

    public List<JobSkill> getAllJobSkills() {
        Response response = rt.getForObject(URI.create(uri), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<JobSkill>>() {});
    }
}