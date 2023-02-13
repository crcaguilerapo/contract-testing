package co.cristian.customer.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Beans {
    @Bean
    RestTemplate paymentRestTemplate(@Value("${product-service.base-url") String uri) {
        return new RestTemplateBuilder().rootUri(uri).build();
    }
}
