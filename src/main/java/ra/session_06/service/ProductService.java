package ra.session_06.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.session_06.dto.ProductPagination;
import ra.session_06.entity.Product;
import ra.session_06.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductPagination getAllProducts(Pageable pageable, String searchName) {
        Page<Product> page;
        if (searchName != null && !searchName.isEmpty()) {
            page = repository.findByNameContainingIgnoreCase(searchName, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return new ProductPagination(
                page.getContent(),
                page.getTotalPages(),
                page.getSize(),
                page.getNumber()
        );
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = getProductById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());
        existing.setStock(product.getStock());
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}