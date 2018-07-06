package shop.systems.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import shop.integration.dto.ShopDTO;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

@Component
@MessageEndpoint
public class Dummy2EndPoint {
//
//  @Bean
//    @ServiceActivator(inputChannel = "almaChannel", autoStartup = "true")
//    public CharacterStreamWritingMessageHandler logwriter(@Payload ShopDTO payload){
//        CharacterStreamWritingMessageHandler handler = new CharacterStreamWritingMessageHandler(new BufferedWriter(new OutputStreamWriter(System.out)));
//       System.out.println(payload);
//        return handler;
//
//    }



    @ServiceActivator(inputChannel = "almaChannel", autoStartup = "true")
    public void logwriter(ShopDTO payload){

        System.out.println(payload.getShopName()+" <------------");
    }



}
