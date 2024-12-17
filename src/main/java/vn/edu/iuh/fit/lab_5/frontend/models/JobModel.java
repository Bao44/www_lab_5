package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.lab_5.backend.models.Candidate;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.JobSkill;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.services.JobServices;

import java.net.URI;
import java.util.List;

@Component
public class JobModel {

    private final RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/job";
    private final ObjectMapper mapper = new ObjectMapper();

    public Job addJob(Job job) {
        Response response = rt.postForObject(URI.create(uri), job, Response.class);
        return mapper.convertValue(response.getData(), Job.class);
    }

    public Job getJobById(Long id) {
        Response response = rt.getForObject(URI.create(uri + "/" + id), Response.class);
        return mapper.convertValue(response.getData(), Job.class);
    }

    public List<Job> getAllJobs() {
        Response response = rt.getForObject(URI.create(uri), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
        });
    }

    public List<Job> getJobForPage(int page) {
        Response response = rt.getForObject(URI.create(uri + "/page/" + page), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<>() {
        });
    }

    public List<Job> searchJobsByNameOrCompany(String searchTerm) {
        String searchUrl = uri + "/search?search=" + searchTerm;
        Response response = rt.getForObject(searchUrl, Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
        });
    }

    public List<Job> getJobsByCompanyId(Long companyId) {
        Response response = rt.getForObject(URI.create(uri + "/company/" + companyId), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
        });
    }

    public List<CandidateDTO> getCandidatesMatchJob(Long jobId) {
        Response response = rt.getForObject(URI.create(uri + "/" + jobId + "/candidates-match-job"), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<CandidateDTO>>() {
        });
    }
}
