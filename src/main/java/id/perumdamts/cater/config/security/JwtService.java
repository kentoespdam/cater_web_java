package id.perumdamts.cater.config.security;

import id.perumdamts.cater.dto.commons.appwrite.AppwriteUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final WebClient webClient;
    @Value("${appwrite.endpoint}")
    private String endpoint;
    @Value("${appwrite.project_id}")
    private String project_id;
    @Value("${appwrite.api_key}")
    private String api_key;

    public Mono<AppwriteUser> getUser(String token) {
        try {
            return webClient.get()
                    .uri(endpoint + "/v1/account/jwt")
                    .header("X-Appwrite-Project", project_id)
                    .header("X-Appwrite-Key", api_key)
                    .header("X-Appwrite-JWT", token)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new JwtAuthenticationException("invalid token")))
                    .bodyToMono(AppwriteUser.class);
//                    .onErrorMap(Mono.error(new JwtAuthenticationException("invalid token")));
        }catch (Exception e){
            log.error("error get user from token: {}", e.getMessage());
            return Mono.empty();
        }
    }

    public Boolean isTokenValid(String token) {
        return getUser(token) != null;
    }
}
