package shop.systems.gateway;

import org.springframework.messaging.handler.annotation.Payload;
import uni_miskolc.distributed.shop.ProductRegistrationRequest;
import uni_miskolc.distributed.shop.ShopRegistrationRequest;

import java.util.Collection;


public interface ShopCompGateway {

@Payload("")
Collection<ProductRegistrationRequest> listProducts(ShopRegistrationRequest shop);

    void addProductToShop(ProductRegistrationRequest product, ShopRegistrationRequest shop);

    void comparePrices(ShopRegistrationRequest shop1, ShopRegistrationRequest shop2);

}
