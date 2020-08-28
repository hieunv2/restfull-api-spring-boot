package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.Product;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable> {
    Product findById(int id);
}
