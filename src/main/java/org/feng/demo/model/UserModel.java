package org.feng.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserModel implements Serializable {

    private final int id;
    private final String userName;
    private final String type;

    @JsonCreator
    public UserModel(@JsonProperty("id") int id,
                     @JsonProperty("login") String userName,
                     @JsonProperty("type") String type) {
        this.id = id;
        this.userName = userName;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;

        UserModel userModel = (UserModel) o;

        if (id != userModel.id) return false;
        if (!userName.equals(userModel.userName)) return false;
        return type.equals(userModel.type);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userName.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
