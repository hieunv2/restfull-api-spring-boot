package vn.javisco.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "Password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatusFlag statusFlag;

    @Enumerated(EnumType.STRING)
    private UserRoleFlag roleFlag;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserProfile userProfile;

    @JsonIgnore
    public boolean isActive() {
        return statusFlag == UserStatusFlag.ACTIVE;
    }

}
