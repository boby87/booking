package cm.ftg.authServer.services;



import cm.ftg.authServer.dto.LoginDto;

import java.util.Map;

public interface BuildJwtService {
    Map<String, String> buildJWT(LoginDto users);
}
