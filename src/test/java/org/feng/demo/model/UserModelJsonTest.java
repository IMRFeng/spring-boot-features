package org.feng.demo.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class UserModelJsonTest {

    @Autowired private JacksonTester<UserModel> json;

    @Test public void serializeJson() throws Exception {
        UserModel userModel = new UserModel(111, "VictorFeng", "owner");
        assertThat(this.json.write(userModel)).hasJsonPathNumberValue("@.id");
        assertThat(this.json.write(userModel)).extractingJsonPathNumberValue("$.id").isEqualTo(111);
        assertThat(this.json.write(userModel)).hasJsonPathStringValue("@.userName");
        assertThat(this.json.write(userModel)).extractingJsonPathStringValue("@.userName").isEqualTo("VictorFeng");
        assertThat(this.json.write(userModel)).hasJsonPathStringValue("@.type");
        assertThat(this.json.write(userModel)).extractingJsonPathStringValue("@.type").isEqualTo("owner");
    }

    @Test public void deserializeJson() throws Exception {
        String content = "{\"id\":111,\"userName\":\"VictorFeng\",\"type\":\"owner\"}";

        assertThat(this.json.parse(content)).isEqualTo(new UserModel(111, "VictorFeng", "owner"));
        assertThat(this.json.parseObject(content).getId()).isEqualTo(111);
        assertThat(this.json.parseObject(content).getUserName()).isEqualTo("VictorFeng");
        assertThat(this.json.parseObject(content).getType()).isEqualTo("owner");
    }
}
