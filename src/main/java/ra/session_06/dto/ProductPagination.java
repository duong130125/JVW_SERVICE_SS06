package ra.session_06.dto;

import lombok.*;
import ra.session_06.entity.Product;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPagination {
    private List<Product> products;
    private int totalPage;
    private int pageSize;
    private int currentPage;
}