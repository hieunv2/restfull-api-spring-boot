package vn.javisco.agency.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.entity.UserProfile;
import vn.javisco.agency.entity.UserRoleFlag;
import vn.javisco.agency.exceptions.UserExistException;
import vn.javisco.agency.repository.UserRepository;
import vn.javisco.agency.request.ChangePasswordRequest;

import java.util.List;

@Service
public class UserService extends BaseService<UserLogin> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    protected UserService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public UserLogin findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserLogin create(UserLogin request) {
        UserLogin userLogin = userRepository.findByEmail(request.getEmail());
        if (userLogin != null) throw new UserExistException("USER_EXIST");
        userLogin = new UserLogin();
        BeanUtils.copyProperties(request, userLogin, "id", "password", "createdAt", "updatedAt");
        userLogin.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return userRepository.saveAndFlush(userLogin);
    }

    public UserLogin update(UserLogin request, int id) {
        UserLogin userLogin = userRepository.findById(id);
        BeanUtils.copyProperties(request, userLogin, "id", "email", "password", "createdAt", "updatedAt");
        userLogin = userRepository.saveAndFlush(userLogin);
        return userLogin;
    }

    public void changePassword(ChangePasswordRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserLogin userLogin = userRepository.findByEmail(userDetails.getUsername());
        boolean isMatch = bCryptPasswordEncoder.matches(request.getPassword(), userDetails.getPassword());
        if (!isMatch) throw new BadCredentialsException("PASSWORD_NOT_MATCH");
        userLogin.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        userRepository.saveAndFlush(userLogin);
    }

    public List<UserLogin> getAllAgency() {
        return userRepository.findAllByRoleFlag(UserRoleFlag.ROLE_AGENCY);
    }

    public UserLogin getUserProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserLogin userLogin = userRepository.findByEmail(userDetails.getUsername());
        if (userLogin.getUserProfile() == null) {
            UserProfile userProfile = new UserProfile();
            userProfile.setId(userLogin.getId());
            userLogin.setUserProfile(userProfile);
            userLogin = userRepository.saveAndFlush(userLogin);
        }
        return userLogin;
    }

    public UserLogin updateUserProfile(UserProfile request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserLogin userLogin = userRepository.findByEmail(userDetails.getUsername());
        UserProfile userProfile = userLogin.getUserProfile();
        BeanUtils.copyProperties(request, userProfile, "id", "createdAt", "updatedAt");
        userLogin.setUserProfile(userProfile);
        userLogin = userRepository.saveAndFlush(userLogin);
        return userLogin;
    }
}
