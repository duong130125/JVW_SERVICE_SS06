package ra.session_06.service;

import org.springframework.stereotype.Service;
import ra.session_06.entity.Order;
import ra.session_06.entity.OrderDetail;
import ra.session_06.entity.Product;
import ra.session_06.repository.OrderDetailRepository;
import ra.session_06.repository.OrderRepository;
import ra.session_06.repository.ProductRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());

        List<OrderDetail> details = order.getOrderDetails();
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Đơn hàng phải có ít nhất một sản phẩm.");
        }

        double total = 0.0;

        for (OrderDetail detail : details) {
            Product product = productRepository.findById(detail.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

            if (product.getStock() < detail.getQuantity()) {
                throw new IllegalArgumentException("Sản phẩm '" + product.getName() +
                        "' không đủ hàng. Tồn kho: " + product.getStock());
            }

            product.setStock(product.getStock() - detail.getQuantity());
            productRepository.save(product);

            detail.setPriceBuy(product.getPrice());
            detail.setOrder(order);

            total += product.getPrice() * detail.getQuantity();
        }

        order.setTotalMoney(total);

        return orderRepository.save(order); // orderDetails sẽ được cascade nếu cấu hình đúng
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return orderRepository.findByCreatedAtBetween(start, end);
    }
}
