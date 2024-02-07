package id.perumdamts.cater.config.security;

import id.perumdamts.cater.dto.commons.appwrite.AppwriteUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
class JwtServiceTest {
    @Autowired
    private WebClient webClient;
    @Value("${appwrite.endpoint}")
    private String endpoint;
    @Value("${appwrite.project_id}")
    private String project_id;
    @Value("${appwrite.api_key}")
    private String api_key;

    @Test
    public void test() {
        System.out.println("url: " + endpoint + "/v1/account/jwt");
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI5MDA4MDA0NTYiLCJzZXNzaW9uSWQiOiI2NWMxZmUzOWIyMWU1ODJiNzg4NyIsImV4cCI6MTcwNzIxNDM0N30.Nnm1_pPosQaJY6njqWViflHARrkkcEdxBvK8O3TVdcQ";
        try {
            AppwriteUser block = webClient.get()
                    .uri(endpoint + "/v1/account/jwt")
                    .header("X-Appwrite-Project", project_id)
                    .header("X-Appwrite-Key", api_key)
                    .header("X-Appwrite-JWT", token)
                    .retrieve()
                    .bodyToMono(AppwriteUser.class)
                    .block();
            System.out.println(block);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}