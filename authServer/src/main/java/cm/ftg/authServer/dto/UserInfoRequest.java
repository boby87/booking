package cm.ftg.authServer.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
}
