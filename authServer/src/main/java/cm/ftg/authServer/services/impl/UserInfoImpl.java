package cm.ftg.authServer.services.impl;

import cm.ftg.authServer.dto.UserInfoRequest;
import cm.ftg.authServer.entities.UserInfo;
import cm.ftg.authServer.mapper.UserInfoMapper;
import cm.ftg.authServer.repository.UserInfoRepository;
import cm.ftg.authServer.services.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserInfoImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUserInfo(UserInfoRequest userInfoRequest) {
        UserInfo userInfo = UserInfoMapper.mapToUserInfo(userInfoRequest);
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        UserInfo info = userInfoRepository.save(userInfo);
        log.info("User saved successfully: {}", info);
    }
}
