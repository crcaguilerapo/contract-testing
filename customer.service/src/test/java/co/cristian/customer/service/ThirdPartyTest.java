package co.cristian.customer.service;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import co.cristian.customer.service.businessLayer.ProductDto;
import co.cristian.customer.service.businessLayer.ThirdPartyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
class ThirdPartyTest {
    @Pact(provider = "product-service", consumer = "customer-service")
    public RequestResponsePact contract(PactDslWithProvider builder) {

        PactDslJsonBody body = new PactDslJsonBody()
                .stringType("name", "nexus")
                .stringType("type", "smartphone")
                .stringType("price", "21.03")
                .close()
                .asBody();

        return builder
                .given("Product with Id: 123")
                .uponReceiving("Test for productById")
                .path("/product/123") /* request */
                .method("GET")
                .willRespondWith() /* response */
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "contract")
    void thirdPartyTest(MockServer mockServer) {
        ProductDto actual = new ProductDto("nexus", "smartphone", "21.03");
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();
        ThirdPartyService thirdPartyService = new ThirdPartyService(restTemplate);
        ProductDto expected = thirdPartyService.getProductsFromOtherService(123);
        assertEquals(expected, actual);
    }
}
