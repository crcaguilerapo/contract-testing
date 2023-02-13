package co.cristian.product.service;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import co.cristian.product.service.businessLayer.ProductDto;
import co.cristian.product.service.businessLayer.ProductService;
import org.apache.http.HttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@Provider("product-service")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PactFolder("build/pacts")
@PactBroker(
		host = "localhost",
		port = "8000",
		authentication = @PactBrokerAuth(username = "test", password = "test")
)
class ProductServiceTest {

	@LocalServerPort
	private int port;

	@MockBean
	private ProductService productService;

	@BeforeEach
	void setUp(PactVerificationContext context) {
		context.setTarget(new HttpTestTarget("localhost", port));
	}

	@TestTemplate
	@ExtendWith(PactVerificationInvocationContextProvider.class)
	void verifyPact(PactVerificationContext context, HttpRequest request) {
		context.verifyInteraction();
	}

	@State("Product with Id: 123")
	public void productWith123() {
		when(productService.getProductById(123))
				.thenReturn(new ProductDto("nexus", "smartphone", "21.03"));
	}
}
