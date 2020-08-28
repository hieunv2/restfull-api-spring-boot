package vn.javisco.agency.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vn.javisco.agency.entity.Order;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.exceptions.NotFoundException;
import vn.javisco.agency.repository.OrderRepository;
import vn.javisco.agency.repository.UserRepository;
import vn.javisco.agency.request.OrderCreateRequest;

@Service
public class OrderService extends BaseService<Order> {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    protected OrderService(OrderRepository repository, UserRepository userRepository) {
        super(repository);
        this.orderRepository = repository;
        this.userRepository = userRepository;
    }

    public Order create(OrderCreateRequest request) {
        int userId = request.getUserId();
        UserLogin userLogin = userRepository.findById(userId);
        if (userLogin == null) {
            throw new NotFoundException("Can not find user");
        }
        Order order = new Order();
        BeanUtils.copyProperties(request, order);
        order.setUser(userLogin);
        return orderRepository.saveAndFlush(order);
    }

    public Order update(OrderCreateRequest request, int id) {
        Order oldOrder = orderRepository.findById(id);
        if (oldOrder == null) throw new NotFoundException("Cant not find order");
        int userId = request.getUserId();
        UserLogin userLogin = userRepository.findById(userId);
        if (userLogin == null) {
            throw new NotFoundException("Can not find user");
        }
        Order order = new Order();
        BeanUtils.copyProperties(request, order, "id", "created_at", "updated_at");
        order.setUser(userLogin);
        return orderRepository.saveAndFlush(order);
    }

}