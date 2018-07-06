package shop.systems.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@MessageEndpoint
public class DummyGatewayHelper {

//    @Bean
//    @ServiceActivator(inputChannel = "dummyGatewayRequestChannel")
//    public HttpRequestExecutingMessageHandler httpGateway(){
//        HttpRequestExecutingMessageHandler gateway = new HttpRequestExecutingMessageHandler("http://localhost:8088/shop/");
//        gateway.setHttpMethod(HttpMethod.GET);
//        gateway.setExpectedResponseType(ShopDTO.class);
//        gateway.setOutputChannel(almaChannel());
//        return gateway;
//    }


    //TODO ProductDTO-kat mozgatni és feldolgozni


    @Router(inputChannel = "dummyGatewayRequestChannel")
    public String route(@Header(value = "METHOD_NAME", required = false) String methodName) {
        if ("TIMESTAMP".equals(methodName)) {
            return "kiirChannel";
        } else if ("PRODUCT_QUERY".equals(methodName)) {
            return "getShopInfoChannel";

        }
        return "hibaChannel";


    }


    @Bean
    public MessageChannel prodQueryChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel prodQueryReplyChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel getShopInfoChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel kiirChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel hibaChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel dummyGatewayRequestChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "kiirChannel", autoStartup = "true")
    public CharacterStreamWritingMessageHandler logwriter() {
//       System.out.println(payload);
        return new CharacterStreamWritingMessageHandler(new BufferedWriter(new OutputStreamWriter(System.out)));

    }


    @Bean
    @ServiceActivator(inputChannel = "hibaChannel", autoStartup = "true")
    public CharacterStreamWritingMessageHandler logwriter00() {

//       System.out.println(payload);
        return new CharacterStreamWritingMessageHandler(new BufferedWriter(new OutputStreamWriter(System.err)));

    }


    @Bean
    @ServiceActivator(inputChannel = "getShopInfoChannel")
    public HttpRequestExecutingMessageHandler httpGateway() {
        HttpRequestExecutingMessageHandler gateway = new HttpRequestExecutingMessageHandler("http://localhost:8088/shop/");
        gateway.setHttpMethod(HttpMethod.GET);
        gateway.setExpectedResponseType(ShopDTO.class);
        gateway.setOutputChannel(prodQueryChannel());

        return gateway;
    }


    @ServiceActivator(inputChannel = "prodQueryChannel", autoStartup = "true", outputChannel = "prodQueryReplyChannel")
    public List<ProductDTO> prodWriter(ShopDTO shopDTO) {

        System.out.println("1");
        List<ProductDTO> productDTOS = new ArrayList<>();
        productDTOS.addAll(shopDTO.getProductList().getProduct());
        return productDTOS;
    }
}
