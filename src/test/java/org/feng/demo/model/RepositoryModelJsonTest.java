package org.feng.demo.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class RepositoryModelJsonTest {

    @Autowired private JacksonTester<RepositoryModel> json;

    @Autowired private JacksonTester<UserModel> userJson;

    @Test
    public void serializeJson() throws Exception {
        UserModel userModel = new UserModel(111, "VictorFeng", "owner");
        RepositoryModel repositoryModel = new RepositoryModel(222, "spring-boot-features", "",
                "desc", new Date(), new Date(), userModel);

        assertThat(this.json.write(repositoryModel)).hasJsonPathNumberValue("@.id");
        assertThat(this.json.write(repositoryModel)).extractingJsonPathNumberValue("$.id").isEqualTo(222);
        assertThat(this.json.write(repositoryModel)).hasJsonPathStringValue("@.name");
        assertThat(this.json.write(repositoryModel)).extractingJsonPathStringValue("@.name").isEqualTo("spring-boot-features");
        assertThat(this.json.write(repositoryModel)).hasJsonPathStringValue("@.htmlUrl");
        assertThat(this.json.write(repositoryModel)).extractingJsonPathStringValue("@.htmlUrl").isEqualTo("");
        assertThat(this.json.write(repositoryModel)).hasJsonPathStringValue("@.description");
        assertThat(this.json.write(repositoryModel)).extractingJsonPathStringValue("@.description").isEqualTo("desc");
        assertThat(this.json.write(repositoryModel)).hasJsonPathValue("@.createdAt");
        assertThat(this.json.write(repositoryModel)).hasJsonPathValue("@.updatedAt");
        assertThat(this.json.write(repositoryModel)).hasJsonPathValue("@.owner");
    }

    @Test public void deserializeJson() throws Exception {
        String content = "{\"id\":222,\"name\":\"spring-boot-features\",\"htmlUrl\":\"\",\"description\":\"desc\"}";

        assertThat(this.json.parseObject(content).getId()).isEqualTo(222);
        assertThat(this.json.parseObject(content).getName()).isEqualTo("spring-boot-features");
        assertThat(this.json.parseObject(content).getHtmlUrl()).isEqualTo("");
        assertThat(this.json.parseObject(content).getDescription()).isEqualTo("desc");
    }
}
