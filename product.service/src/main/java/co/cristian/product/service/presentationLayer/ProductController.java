package co.cristian.product.service.presentationLayer;

import co.cristian.product.service.businessLayer.ProductDto;
import co.cristian.product.service.businessLayer.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }
}
