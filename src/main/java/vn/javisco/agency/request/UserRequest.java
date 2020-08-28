package vn.javisco.agency.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.javisco.agency.entity.UserRoleFlag;
import vn.javisco.agency.entity.UserStatusFlag;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest implements BaseRequest {
    private String name;
    private String email;
    private String password;
    private UserRoleFlag role;
}
