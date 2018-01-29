package shop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;
import shop.systems.gateway.Shop1Gateway;
import shop.systems.gateway.Shop2Gateway;

import java.util.Collection;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        ApplicationContext context = new ClassPathXmlApplicationContext("/si-config.xml");
        MessageChannel stdOut = context.getBean("messageChannel", MessageChannel.class);

        Message<String> message = MessageBuilder.withPayload("Hello World!").build();

        System.out.println("Sout says stdOut is:" + stdOut);
        stdOut.send(message);

        Shop1Gateway shop1Gateway = context.getBean("Shop1Gateway", Shop1Gateway.class);
        System.out.println(shop1Gateway.listProducts());

        System.out.println("gateway.listProducts.getShopID says:" + shop1Gateway.listProducts().getProductList());


        Shop2Gateway shop2Gateway = context.getBean("Shop2Gateway", Shop2Gateway.class);
        System.out.println(shop2Gateway.listProducts());

        System.out.println("gateway.listProducts.getShopID says:" + shop2Gateway.listProducts().getShopName());

        ShopComparator shopComparator = new ShopComparator();

        ShopDTO shopDTO1 = shop1Gateway.listProducts();
        ShopDTO shopDTO2 = shop2Gateway.listProducts();


        Collection<ProductDTO> bestProd;

        bestProd = shopComparator.getBestPrices(shopDTO1, shopDTO2);

        System.out.println(bestProd);
        for (ProductDTO productDTO : bestProd) {
            System.out.println(productDTO.getItemName());
            System.out.println(productDTO.getPrice());
            System.out.println(productDTO.getManufacturer() + "\n");


        }




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











