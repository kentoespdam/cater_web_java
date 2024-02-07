package id.perumdamts.cater.config.security;

import id.perumdamts.cater.dto.commons.appwrite.AppwriteUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadesImpl implements AuthenticationFacades {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public AppwriteUser getUser() {
        return (AppwriteUser) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
