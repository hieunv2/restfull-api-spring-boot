package vn.javisco.agency.response.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginSuccessResponse implements LoginResponse {
    private String email;
    private String token;
}
