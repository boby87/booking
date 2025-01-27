package cm.ftg.authServer.services.impl;


import cm.ftg.authServer.dto.LoginDto;
import cm.ftg.authServer.entities.UserInfo;
import cm.ftg.authServer.repository.UserInfoRepository;
import cm.ftg.authServer.services.BuildJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildJwtImpl implements BuildJwtService {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserInfoRepository userRepository;


    @Override
    public Map<String, String> buildJWT(LoginDto userLogin) {
        String subject;
        String scope;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password())
        );
        subject=authentication.getName();
        scope=authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        UserInfo users = userRepository.findByEmail(userLogin.email()).orElseThrow();
        Map<String, String> idToken=new HashMap<>();
        String  jwtAccessToken =buildToken(subject, scope, users);
        idToken.put("accessToken",jwtAccessToken);

        return idToken;
    }

    private String buildToken(String subject, String scope, UserInfo users) {
        Instant instant=Instant.now();

        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(50000, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .claim("reference", users.getReference())
                .claim("email", users.getEmail())
                .claim("firstName", users.getFirstName())
                .claim("lastName", users.getLastName())
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }


}
