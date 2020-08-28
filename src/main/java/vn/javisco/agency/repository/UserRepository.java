package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.entity.UserRoleFlag;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Serializable> {
    UserLogin findByEmail(String email);

    UserLogin findById(int id);

    List<UserLogin> findAllByRoleFlag(UserRoleFlag userRoleFlag);
}
