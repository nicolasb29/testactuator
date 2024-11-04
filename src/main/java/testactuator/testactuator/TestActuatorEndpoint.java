package testactuator.testactuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class TestActuatorEndpoint {

    @ReadOperation
    public String read() {
        return "OK";
    }
}
