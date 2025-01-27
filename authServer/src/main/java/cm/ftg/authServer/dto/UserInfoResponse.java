package cm.ftg.authServer.dto;

import cm.ftg.authServer.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse extends BaseEntity {
    private String reference;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String role;
    private boolean state;

}
