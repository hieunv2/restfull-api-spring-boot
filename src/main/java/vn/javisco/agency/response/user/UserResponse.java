package vn.javisco.agency.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import vn.javisco.agency.entity.UserRoleFlag;
import vn.javisco.agency.entity.UserStatusFlag;
import vn.javisco.agency.response.base.BaseResponse;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserResponse implements BaseResponse {
    private int id;

    private String name;

    private String email;

    private boolean active;

    private UserStatusFlag statusFlag;

    private UserRoleFlag roleFlag;
}
