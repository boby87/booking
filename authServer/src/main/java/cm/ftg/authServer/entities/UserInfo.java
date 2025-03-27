package cm.ftg.authServer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private UUID reference;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @Column(nullable = false)
    private String role;
    private boolean state;

}
