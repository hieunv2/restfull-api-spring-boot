package vn.javisco.agency.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.request.ChangePasswordRequest;
import vn.javisco.agency.request.LoginRequest;
import vn.javisco.agency.response.login.LoginResponse;
import vn.javisco.agency.response.login.LoginSuccessResponse;
import vn.javisco.agency.security.JWTUtils;
import vn.javisco.agency.security.JwtUserDetailService;
import vn.javisco.agency.services.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtTokenUtil;
    private final JwtUserDetailService userDetailsService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtTokenUtil, JwtUserDetailService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        LoginResponse loginResponse = new LoginSuccessResponse(userDetails.getUsername(), token);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<UserLogin> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("WRONG_PASSWORD", e);
        }
    }
}
