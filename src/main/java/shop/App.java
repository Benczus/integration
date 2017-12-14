package shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import shop.systems.gateway.ShopCompGateway;
import uni_miskolc.distributed.shop.ProductRegistrationRequest;
import uni_miskolc.distributed.shop.ShopRegistrationRequest;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("si-config.xml");
        MessageChannel stdOut = context.getBean("messageChannel", MessageChannel.class);


        Message<String> message= MessageBuilder.withPayload("Channel").build();
        stdOut.send(message);

        ShopCompGateway gateway= (ShopCompGateway) context.getBean("ShopCompGateway", ShopCompGateway.class);

        ShopRegistrationRequest shop = new ShopRegistrationRequest();

        for (ProductRegistrationRequest product : gateway.listProducts(shop) ) {
            System.out.println(product.getItemName()+" "+ product.getItemID()+" "+product.getManufacturer());
        }





        }
    }











}