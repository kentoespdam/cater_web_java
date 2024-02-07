package id.perumdamts.cater.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (authentication.isAuthenticated())
            return Mono.just(authentication);

        return Mono.just(authentication)
                .cast(JwtToken.class)
                .filter(jwtToken -> jwtService.isTokenValid(jwtToken.getToken()))
                .map(JwtToken::withAuthenticated)
                .switchIfEmpty(Mono.defer(this::raiseBadCredentials));
//                .switchIfEmpty(Mono.empty());
    }

    private <T> Mono<T> raiseBadCredentials() {
        return Mono.error(new JwtAuthenticationException("Invalid Credentials"));
    }
}
