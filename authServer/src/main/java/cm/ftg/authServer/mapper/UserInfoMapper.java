package cm.ftg.authServer.mapper;

import cm.ftg.authServer.dto.UserInfoRequest;
import cm.ftg.authServer.dto.UserInfoResponse;
import cm.ftg.authServer.entities.UserInfo;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public final class UserInfoMapper {
  public static UserInfo mapToUserInfo(UserInfoRequest userInfoRequest) {

        return UserInfo.builder()
                .firstName(userInfoRequest.getFirstName())
                .reference(UUID.randomUUID())
                .lastName(userInfoRequest.getLastName())
                .phoneNumber(userInfoRequest.getPhoneNumber())
                .email(userInfoRequest.getEmail())
                .password(userInfoRequest.getPassword())
                .role(userInfoRequest.getRole())
                .build();
    }

  public static UserInfoResponse mapToUserInfoResponse(UserInfo userInfo) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }
}
