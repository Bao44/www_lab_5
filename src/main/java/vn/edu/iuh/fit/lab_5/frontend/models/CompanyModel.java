package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.lab_5.backend.models.Company;
import vn.edu.iuh.fit.lab_5.backend.models.Response;

import java.net.URI;
import java.util.List;

@Component
public class CompanyModel {
    private RestTemplate rt = new RestTemplate();
    private final String url = "http://localhost:8080/api/company";
    private ObjectMapper mapper = new ObjectMapper();

    public List<Company> getAllCompanies() {
        Response response = rt.getForObject(URI.create(url), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Company>>() {});
    }


}
