package cm.ftg.authServer.api;


import cm.ftg.authServer.constants.Constants;
import cm.ftg.authServer.dto.LoginDto;
import cm.ftg.authServer.dto.ResponseDto;
import cm.ftg.authServer.dto.UserInfoRequest;
import cm.ftg.authServer.services.BuildJwtService;
import cm.ftg.authServer.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("user")
@Validated

public class UserInfoApi {
private final BuildJwtService buildJwtService;
private final UserInfoService userInfoService;
    public UserInfoApi(BuildJwtService buildJwtService, UserInfoService userInfoService) {
        this.buildJwtService = buildJwtService;
        this.userInfoService = userInfoService;
    }


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> create(@RequestBody UserInfoRequest request) {
        userInfoService.createUserInfo(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> jwtToken(@RequestBody LoginDto userLogin) {
        return new ResponseEntity<>(buildJwtService.buildJWT(userLogin), HttpStatus.OK);
    }
}
