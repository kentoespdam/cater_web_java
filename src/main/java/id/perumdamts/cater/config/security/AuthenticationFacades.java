package id.perumdamts.cater.config.security;

import id.perumdamts.cater.dto.commons.appwrite.AppwriteUser;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacades {
    Authentication getAuthentication();
    AppwriteUser getUser();
}
