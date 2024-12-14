package vn.edu.iuh.fit.lab_5.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import vn.edu.iuh.fit.lab_5.backend.models.Response;
import vn.edu.iuh.fit.lab_5.backend.models.Skill;

import java.net.URI;
import java.util.List;

@Component
public class SkillModel {
    private RestTemplate rt = new RestTemplate();
    private final String uri = "http://localhost:8080/api/skill";
    private ObjectMapper mapper = new ObjectMapper();

    public Skill addSkill(Skill skill) {
        Response response = rt.postForObject(URI.create(uri), skill, Response.class);
        return mapper.convertValue(response.getData(), Skill.class);
    }

    public List<Skill> getAllSkills() {
        Response response = rt.getForObject(URI.create(uri), Response.class);
        return mapper.convertValue(response.getData(), new TypeReference<List<Skill>>() {});
    }

    public Skill getSkillBySkillName(String skillName) {
        Response response = rt.postForObject(URI.create(uri + "/skill-name"), skillName, Response.class);
        return mapper.convertValue(response.getData(), Skill.class);
    }
}
