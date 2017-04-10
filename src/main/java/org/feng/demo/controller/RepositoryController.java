package org.feng.demo.controller;

import org.feng.demo.model.RepositoryModel;
import org.feng.demo.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepositoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryController.class);

    private RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value = "/repositories/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RepositoryModel>> fetchRepositoriesByUserName(@PathVariable String userName) {
        LOGGER.info("Starting to fetch repositories for {}", userName);
        List<RepositoryModel> repositoryModels = this.repositoryService.getRepositoriesByUserName(userName);
        return new ResponseEntity<>(repositoryModels, HttpStatus.OK);
    }
}
