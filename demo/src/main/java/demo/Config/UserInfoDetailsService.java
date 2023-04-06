package demo.Config;

import demo.entity.UserInfo;
import demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo>  finduser=userRepo.findByName(username);
        return finduser.map(userInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username not found "+username));

    }
}
