package shop.systems.gateway;

import org.springframework.integration.annotation.Payload;
import shop.integration.dto.ShopDTO;


public interface Shop2Gateway {

    @Payload("new java.util.Date()")
    ShopDTO listProducts();

//    void addProductToShop1(ProductDTO product);
//    void addProductToShop2(ProductDTO product);
//    void getPrices1(ShopDTO shop1);
//    void getPrices2(ShopDTO shop2);

}
