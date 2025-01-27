package cm.ftg.authServer.services.impl;

import cm.ftg.authServer.entities.UserInfo;
import cm.ftg.authServer.exception.ResourceNotFoundException;
import cm.ftg.authServer.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo user = userInfoRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("L'utilisateur n'a pas été identifié", "email", email));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }


}
