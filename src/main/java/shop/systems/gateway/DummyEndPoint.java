package shop.systems.gateway;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class DummyEndPoint {

    @ServiceActivator(inputChannel = "requestChannel")
    public void echo(String payload){
        System.out.println(payload.toUpperCase());
    }
}
