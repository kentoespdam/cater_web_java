package id.perumdamts.cater.dto.commons.appwrite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppwriteUser {
    @Getter
    private String $id;
    @Getter
    private String $createdAt;
    @Getter
    private String $updatedAt;
    private String name;
    private String registration;
    private Boolean status;
    private String passwordUpdate;
    private String email;
    private String phone;
    private Boolean emailVerification;
    private Boolean phoneVerification;
    private Prefs prefs;

    @Override
    public String toString() {
        return "AppwriteUser{" +
                "$id='" + get$id() + '\'' +
                ", $createdAt='" + get$createdAt() + '\'' +
                ", $updatedAt='" + get$updatedAt() + '\'' +
                ", name='" + getName() + '\'' +
                ", registration='" + getRegistration() + '\'' +
                ", status=" + getStatus() +
                ", passwordUpdate='" + getPasswordUpdate() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", emailVerification=" + getEmailVerification() +
                ", phoneVerification=" + getPhone() +
                ", prefs=" + getPrefs() +
                '}';
    }
}
