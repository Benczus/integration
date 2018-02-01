package shop.systems.gateway;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;
import org.springframework.messaging.Message;
import shop.integration.dto.ShopDTO;

import java.util.ArrayList;


public interface ShopGateway {

    @Payload("new java.util.Date()")
    ArrayList listProducts();

    //    ShopDTO listProductsByShop(Message message);
    String addProductToShop(Message message);

    @Payload("new java.util.Date()")
    ShopDTO listProductsByShop(@Header("shopNumber") String shopId);

}
