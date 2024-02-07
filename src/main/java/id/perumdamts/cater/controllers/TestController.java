package id.perumdamts.cater.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TestController {
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/test")
    Mono<?> index(Authentication authentication) {
        User user= (User) authentication.getPrincipal();
        return Mono.just(user);
//        return Mono.just("OK");
    }
}
