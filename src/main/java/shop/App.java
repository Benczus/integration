package shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;
import shop.systems.gateway.ShopGateway;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/si-config.xml");
        MessageChannel stdOut = context.getBean("messageChannel", MessageChannel.class);

        Message<String> message = MessageBuilder.withPayload("Hello World!").build();




//        MessageChannel stdOut = context.getBean("shop1ReceiverChannel", MessageChannel.class);
//
//        Message<String> message = MessageBuilder.withPayload("Hello World!").build();



        System.out.println("Sout says stdOut is:" + stdOut);
        stdOut.send(message);

        ShopGateway shopGateway = context.getBean("ShopGateway", ShopGateway.class);
       System.out.println(shopGateway.listProducts());

        ShopComparator shopComparator = new ShopComparator();


        ArrayList<ShopDTO> shopList = shopGateway.listProducts();
        ShopDTO shopDTO1 = shopList.get(0);
        ShopDTO shopDTO2 = shopList.get(1);


        System.out.println("FIRST SHOP DATA:");

        for (ProductDTO productDTO : shopDTO1.getProductList().getProduct()
                ) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n \n");

        }

        System.out.println("SECOND SHOP DATE");
        for (ProductDTO productDTO : shopDTO2.getProductList().getProduct()
                ) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n \n");

        }


        System.out.println("cicus cicus " + shopDTO1 + " " + shopDTO2);

        Collection<ProductDTO> bestProd;

        bestProd = shopComparator.getBestPrices(shopDTO1, shopDTO2);

        System.out.println(bestProd);
        for (ProductDTO productDTO : bestProd) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n");


        }


//        Message message1= MessageBuilder.withPayload("cica").setHeader("shopNumber", "shop2").build();


//        ShopDTO shopDTO= shopGateway.listProductsByShop(message1);
        ShopDTO shopDTO = shopGateway.listProductsByShop("shop1");
        System.out.println(shopDTO.getShopID() + "" + shopDTO.getShopName());
        // System.out.println(shopDTO.getShopID());





//   TODO NOT WORKING
//        for (ProductDTO product : gateway.listProducts()) {
//            System.out.println(product.getItemName()+" "+ product.getItemID()+" "+product.getManufacturer());
//        }

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











