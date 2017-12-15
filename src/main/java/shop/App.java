package shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import shop.systems.gateway.ShopCompGateway;
import shop.integration.dto.*;

import java.util.UUID;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("si-config.xml");
        MessageChannel stdOut =(MessageChannel) context.getBean("messageChannel", MessageChannel.class);

        Message<String> message= MessageBuilder.withPayload("Channel").build();

        System.out.println(stdOut);
        //stdOut.send(message);

        ShopCompGateway gateway= (ShopCompGateway) context.getBean("ShopCompGateway", ShopCompGateway.class);

        ShopDTO shop = new ShopDTO();

//   TODO NOT WORKING
//        for (ProductDTO product : gateway.listPbence
// roducts(shop) ) {
//            System.out.println(product.getItemName()+" "+ product.getItemID()+" "+product.getManufacturer());
//        }
//
//        ProductDTO.Warranty warranty= new ProductDTO.Warranty();
//        ProductDTO.Warranty.Private privatea=new ProductDTO.Warranty.Private();
//        privatea.setCompany(2);
//        privatea.setValue(4);
//        warranty.setPrivate(privatea);
//        ProductDTO prod= new ProductDTO();
//        prod.setItemName("CICA");
//        prod.setItemID(UUID.randomUUID().toString());
//        prod.setManufacturer("Macsek");
//        prod.setDescription("");
//        prod.setWarranty(warranty);
//        prod.setPrice(255);
//
//        gateway.addProductToShop(prod, new ShopDTO());

        }
    }











