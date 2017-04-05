package org.feng.demo.service;

import org.feng.demo.model.RepositoryModel;

import java.util.List;

public interface RepositoryService {

    List<RepositoryModel> getRepositoriesByUserName(String userName);

}
