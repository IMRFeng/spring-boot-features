package org.feng.demo.service.impl;

import org.feng.demo.model.RepositoryModel;
import org.feng.demo.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("repositoryService")
public class RepositoryServiceImpl implements RepositoryService {

    private RestTemplate restTemplate;

    @Value("${github.get.repos.endpoint}")
    private String reposForSpecifiedUser;

    @Autowired
    public RepositoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    @Cacheable(value = "repositories", key = "#userName")
    public List<RepositoryModel> getRepositoriesByUserName(String userName) {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("username", userName);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(reposForSpecifiedUser)
                                .queryParam("sort", "created")
                                .queryParam("direction", "desc");

        ResponseEntity<List<RepositoryModel>> repositoryModels = this.restTemplate.exchange(
                builder.buildAndExpand(uriParams).toUri(), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<RepositoryModel>>() {});

        return repositoryModels.getBody();
    }
}
