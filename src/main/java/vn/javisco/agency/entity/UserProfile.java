package vn.javisco.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseEntity {
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    private UserGenderFlag genderFlag;

    private int fileId;

    private String phone;

    private String address;

}
