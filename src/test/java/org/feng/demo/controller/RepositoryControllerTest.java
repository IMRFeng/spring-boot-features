package org.feng.demo.controller;

import org.feng.demo.model.RepositoryModel;
import org.feng.demo.service.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RepositoryController.class)
public class RepositoryControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private RepositoryService repositoryService;

    @Test public void getRepositoriesByUserName() throws Exception {
        given(this.repositoryService.getRepositoriesByUserName(anyString())).willReturn(getStubbedRepositoryList());

        this.mvc.perform(MockMvcRequestBuilders.get("/repositories/ivictorfeng")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(112)))
                .andExpect(jsonPath("$[0].name", is("spring-boot-features")))
                .andExpect(jsonPath("$[0].htmlUrl", is("")))
                .andExpect(jsonPath("$[0].description", is("for integration test")));
    }

    private List<RepositoryModel> getStubbedRepositoryList() {
        List<RepositoryModel> repositories = new ArrayList<>(1);
        RepositoryModel repository = new RepositoryModel(112, "spring-boot-features", "",
                "for integration test", null, null, null);
        repositories.add(repository);

        return repositories;
    }
}
