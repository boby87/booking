package cm.ftg.authServer.services;

import cm.ftg.authServer.dto.UserInfoRequest;

public interface UserInfoService {
    void createUserInfo(UserInfoRequest userInfoRequest);
}
