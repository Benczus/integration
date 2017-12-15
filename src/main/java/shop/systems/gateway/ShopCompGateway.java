package shop.systems.gateway;

import org.springframework.messaging.handler.annotation.Payload;
import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;


import java.util.Collection;


public interface ShopCompGateway {

@Payload("")
Collection<ProductDTO> listProducts(ShopDTO shop);

    void addProductToShop(ProductDTO product, ShopDTO shop);

    void comparePrices(ShopDTO shop1,ShopDTO shop2);

}
