package org.feng.demo.service;

import org.feng.demo.model.RepositoryModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(RepositoryService.class)
@TestPropertySource(properties = "github.get.repos.endpoint=http://github/users/{username}/repos")
public class RepositoryServiceTest {

    private static final String USER_NAME = "ivictorfeng";

    @Autowired private MockRestServiceServer server;

    @Autowired private RepositoryService service;

    @Test public void getRepositoryListByUserName() {
        this.server.expect(requestTo("http://github/users/" + USER_NAME + "/repos?sort=created&direction=desc"))
                .andRespond(withSuccess(getClassPathResource("repositories.json"), MediaType.APPLICATION_JSON));
        List<RepositoryModel> repositoryModelList = this.service.getRepositoriesByUserName(USER_NAME);

        assertThat(repositoryModelList).isNotNull();
        assertThat(repositoryModelList.size()).isEqualTo(2);
    }

    private ClassPathResource getClassPathResource(String path) {
        return new ClassPathResource(path, getClass());
    }
}
