package id.perumdamts.cater.config.security;

import id.perumdamts.cater.dto.commons.appwrite.AppwriteUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {
    private final JwtService jwtService;
    private static final String BEARER = "Bearer ";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.substring(BEARER.length()))
                .flatMap(token -> createUserDetails(token).map(userDetails -> new JwtToken(token, userDetails)));
    }

    private Mono<UserDetails> createUserDetails(String token) {
        return jwtService.getUser(token)
                .map(appwriteUser -> User
                        .builder()
                        .username(appwriteUser.getEmail())
                        .password(token)
                        .authorities(createAuthorities(appwriteUser)).build()
                ).switchIfEmpty(Mono.empty());
    }

    private Collection<SimpleGrantedAuthority> createAuthorities(AppwriteUser user) {
        return user.getPrefs().getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
