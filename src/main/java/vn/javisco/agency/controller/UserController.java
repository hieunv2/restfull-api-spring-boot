package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.entity.UserProfile;
import vn.javisco.agency.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<UserLogin> {

    private final UserService userService;

    public UserController(UserService service, UserService userService) {
        super(service);
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserLogin> create(@Valid @RequestBody UserLogin userLogin) {
        userLogin = userService.create(userLogin);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserLogin> update(@RequestBody UserLogin request, @PathVariable("id") int id) {
        UserLogin userLogin = userService.update(request, id);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserLogin> userProfile() {
        UserLogin user = userService.getUserProfile();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserLogin> updateUserProfile(@RequestBody UserProfile request) {
        UserLogin user = userService.updateUserProfile(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/agency")
    public ResponseEntity<List<UserLogin>> getAgency() {
        List<UserLogin> list = userService.getAllAgency();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
