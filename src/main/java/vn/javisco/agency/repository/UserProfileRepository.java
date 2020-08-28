package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.UserProfile;

import java.io.Serializable;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Serializable> {
    UserProfile findById(int id);
}
