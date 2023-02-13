package co.cristian.customer.service.businessLayer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ThirdPartyService {
    private final RestTemplate paymentRestTemplate;

    public ThirdPartyService(RestTemplate paymentRestTemplate) {
        this.paymentRestTemplate = paymentRestTemplate;
    }

    public ProductDto getProductsFromOtherService(int id) {
        return paymentRestTemplate.getForObject("/product/" + id, ProductDto.class);
    }
}
