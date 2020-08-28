package vn.javisco.agency.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest implements BaseRequest {
    @NotEmpty
    private String password;
    @NotEmpty
    private String newPassword;
}
