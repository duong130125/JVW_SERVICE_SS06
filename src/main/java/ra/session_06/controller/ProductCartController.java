package ra.session_06.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.session_06.entity.ProductCart;
import ra.session_06.entity.User;
import ra.session_06.service.ProductCartService;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class ProductCartController {

    private final ProductCartService service;

    public ProductCartController(ProductCartService service) {
        this.service = service;
    }

    // Tạm thời truyền User trực tiếp từ body để đơn giản (thực tế nên lấy từ auth token)
    @GetMapping
    public ResponseEntity<List<ProductCart>> getCartItems(@RequestBody User user) {
        return ResponseEntity.ok(service.getCartItemsByUser(user));
    }

    @PostMapping
    public ResponseEntity<ProductCart> addToCart(@RequestBody ProductCart productCart) {
        return ResponseEntity.ok(service.addToCart(productCart));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCart> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(service.updateQuantity(id, quantity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        service.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}