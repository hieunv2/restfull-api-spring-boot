package vn.javisco.agency.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vn.javisco.agency.entity.Order;
import vn.javisco.agency.entity.OrderItem;
import vn.javisco.agency.entity.Product;
import vn.javisco.agency.entity.UserLogin;
import vn.javisco.agency.exceptions.NotFoundException;
import vn.javisco.agency.repository.OrderItemRepository;
import vn.javisco.agency.repository.OrderRepository;
import vn.javisco.agency.repository.ProductRepository;
import vn.javisco.agency.request.OrderCreateRequest;
import vn.javisco.agency.request.OrderItemRequest;

@Service
public class OrderItemService extends BaseService<OrderItem> {

    private final OrderItemRepository orderItemRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;


    protected OrderItemService(OrderItemRepository repository, OrderRepository orderRepository, ProductRepository productRepository) {
        super(repository);
        this.orderItemRepository = repository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItem create(OrderItemRequest request) {

        //set Order
        int orderId = request.getOrderId();
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new NotFoundException("Can not find order");
        }

        //set Product
        int productId = request.getProductId();
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Can not find product");
        }
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(request, order);
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        return orderItemRepository.saveAndFlush(orderItem);
    }

    public OrderItem update(OrderItemRequest request, int id) {
        //get old orderItem
        OrderItem oldOrderItem = orderItemRepository.findById(id);
        if (oldOrderItem == null) throw new NotFoundException("Cant not find order item");

        //set Order
        int orderId = request.getOrderId();
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new NotFoundException("Can not find order");
        }

        //set Product
        int productId = request.getProductId();
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new NotFoundException("Can not find product");
        }
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(request, orderItem,"id","created_at","updated_at");
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        return orderItemRepository.saveAndFlush(orderItem);
    }
}