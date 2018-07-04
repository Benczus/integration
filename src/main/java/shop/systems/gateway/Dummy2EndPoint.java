package shop.systems.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

@Component
@MessageEndpoint
public class Dummy2EndPoint {

    @Bean
    @ServiceActivator(inputChannel = "almaChannel", autoStartup = "true")
    public CharacterStreamWritingMessageHandler logwriter(String payload){
        CharacterStreamWritingMessageHandler handler = new CharacterStreamWritingMessageHandler(new BufferedWriter(new OutputStreamWriter(System.out)));
//        System.out.println(payload.toLowerCase());
        return handler;

    }



}
