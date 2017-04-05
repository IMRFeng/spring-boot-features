package org.feng.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryModel implements Serializable {
    private final int id;
    private final String name;
    private final String htmlUrl;
    private final String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date updatedAt;
    private final UserModel owner;

    @JsonCreator
    public RepositoryModel(@JsonProperty("id") int id,
                           @JsonProperty("name") String name,
                           @JsonProperty("html_url") String htmlUrl,
                           @JsonProperty("description") String description,
                           @JsonProperty("created_at") Date createdAt,
                           @JsonProperty("updated_at") Date updatedAt,
                           @JsonProperty("owner") UserModel owner) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public UserModel getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepositoryModel)) return false;

        RepositoryModel repositoryModel = (RepositoryModel) o;

        if (id != repositoryModel.id) return false;
        if (!name.equals(repositoryModel.name)) return false;
        return description != null ? description.equals(repositoryModel.description) : repositoryModel.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
