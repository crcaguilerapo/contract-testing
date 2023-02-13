package co.cristian.product.service.businessLayer;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public ProductDto getProductById(int id) {
        return new ProductDto("nexus", "smartphone", "21.03");
    }
}
