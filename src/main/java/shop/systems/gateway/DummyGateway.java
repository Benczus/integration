package shop.systems.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import shop.integration.dto.ProductDTO;

import java.util.List;

@MessagingGateway(name = "dummyGateway", defaultRequestChannel = "dummyGatewayRequestChannel")
public interface DummyGateway {

    @Gateway(headers = {@GatewayHeader(name = "METHOD_NAME", value = "TIMESTAMP")})
    @Payload(value = "new java.util.Date()")
     void dummyMethod();

    @Gateway(headers = {@GatewayHeader(name = "METHOD_NAME", value = "PRODUCT_QUERY")}, replyChannel = "prodQueryReplyChannel")
    @Payload(value = "new java.util.Date()")
     List<ProductDTO> listProducts();
}
