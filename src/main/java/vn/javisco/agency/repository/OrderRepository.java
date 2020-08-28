package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.Order;

import java.io.Serializable;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {
    Order findById(int id);
}
