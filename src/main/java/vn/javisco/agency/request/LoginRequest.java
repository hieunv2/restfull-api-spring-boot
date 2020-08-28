package vn.javisco.agency.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest implements BaseRequest {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
