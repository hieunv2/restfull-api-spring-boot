package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.Category;

import java.io.Serializable;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Serializable> {
    Category findById(int id);
}
