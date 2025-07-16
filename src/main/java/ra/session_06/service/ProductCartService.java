package ra.session_06.service;

import org.springframework.stereotype.Service;
import ra.session_06.entity.ProductCart;
import ra.session_06.entity.User;
import ra.session_06.repository.ProductCartRepository;

import java.util.List;

@Service
public class ProductCartService {
    private final ProductCartRepository repository;

    public ProductCartService(ProductCartRepository repository) {
        this.repository = repository;
    }

    public List<ProductCart> getCartItemsByUser(User user) {
        return repository.findByUser(user);
    }

    public ProductCart addToCart(ProductCart productCart) {
        return repository.save(productCart);
    }

    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));
        item.setQuantity(quantity);
        return repository.save(item);
    }

    public void removeFromCart(Long id) {
        repository.deleteById(id);
    }
}