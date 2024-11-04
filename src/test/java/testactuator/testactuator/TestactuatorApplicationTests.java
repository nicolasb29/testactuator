package testactuator.testactuator;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestactuatorApplicationTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setup() {
		RestAssured.basePath = "/actuator";
		RestAssured.port = port;
	}

	@Test
	public void testCustomEndpoint() {
		get("/custom")
				.then()
				.statusCode(200)
				.body(equalTo("OK"));
	}

	@Test
	public void testHealthEndpoint() {
		assertEquals("UP", get("/health")
				.then()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getString("status"));
	}

	@Test
	public void testNoInfoEndpoint() {
		get("/info")
				.then()
				.statusCode(404);
	}
}
