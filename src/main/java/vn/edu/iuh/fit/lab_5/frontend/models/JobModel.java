package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.models.Job;
import vn.edu.iuh.fit.lab_5.backend.models.Response;

import java.net.URI;
import java.util.List;

@Component
public class JobModel {

    private final RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/job";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Job> getAllJob() {
        Response response = rt.getForObject(URI.create(uri), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Job>>() {
        });
    }
}
