package shop.systems.gateway;


import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
