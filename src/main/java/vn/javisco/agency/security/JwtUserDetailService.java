package vn.javisco.agency.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.exceptions.NotFoundException;
import vn.javisco.agency.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserLogin userLogin = userService.findByEmail(userName);
        if (userLogin == null) throw new NotFoundException("USER_NOT_EXIST");
        if (!userLogin.isActive()) throw new DisabledException("USER_DISABLED");

        List<GrantedAuthority> grantList = new ArrayList<>();
        grantList.add(new SimpleGrantedAuthority(userLogin.getRoleFlag().name()));
        return new User(userLogin.getEmail(), userLogin.getPassword(), grantList);
    }
}
