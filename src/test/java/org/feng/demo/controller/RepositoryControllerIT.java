package org.feng.demo.controller;

import org.feng.demo.model.RepositoryModel;
import org.feng.demo.service.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryControllerIT {

    @Autowired private TestRestTemplate restTemplate;

    @MockBean private RepositoryService repositoryService;

    @Test public void getRepositoriesByUserName() {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("userName", "ivictorfeng");

        given(this.repositoryService.getRepositoriesByUserName(anyString())).willReturn(getStubbedRepositoryList());

        ResponseEntity<List<RepositoryModel>> response = this.restTemplate.exchange(
                "/repositories/{userName}", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<RepositoryModel>>() {}, uriParams);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<RepositoryModel> repositoryModelList = response.getBody();
        assertThat(repositoryModelList).isNotNull();
        assertThat(repositoryModelList.size()).isEqualTo(1);
        assertThat(repositoryModelList.get(0).getName()).isNotBlank();
        assertThat(repositoryModelList.get(0).getName()).isEqualTo("spring-boot-features");
        assertThat(repositoryModelList.get(0).getDescription()).isNotBlank();
        assertThat(repositoryModelList.get(0).getDescription()).contains("test");
    }

    private List<RepositoryModel> getStubbedRepositoryList() {
        List<RepositoryModel> repositories = new ArrayList<>(1);
        RepositoryModel repository = new RepositoryModel(112, "spring-boot-features", "",
                "for integration test", null, null, null);
        repositories.add(repository);

        return repositories;
    }
}
