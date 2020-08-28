package vn.javisco.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.javisco.agency.entity.OrderItem;

import java.io.Serializable;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Serializable> {
    OrderItem findById(int id);
}
