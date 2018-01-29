package shop;

import shop.integration.dto.ProductDTO;
import shop.integration.dto.ShopDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ShopComparator {

    ShopComparator() {
    }

    public Collection<ProductDTO> getBestPrices(ShopDTO shop1, ShopDTO shop2, ShopDTO... shops) {

        Collection<ProductDTO> bestPrices = new ArrayList<>(shop1.getProductList().getProduct());


        bestPrices = comparePrices(bestPrices, shop2);


        for (ShopDTO shop : shops
                ) {

            bestPrices = comparePrices(bestPrices, shop);

        }

        return bestPrices;
    }


    private Collection<ProductDTO> comparePrices(Collection<ProductDTO> bestPrices, ShopDTO shop2) {

        int ch = 1;
        Iterator<ProductDTO> iterator1 = shop2.getProductList().getProduct().iterator();
        Iterator<ProductDTO> iterator2 = bestPrices.iterator();
        Iterator<ProductDTO> it = new ArrayList<>(bestPrices).iterator();

        while (iterator1.hasNext()) {
            ProductDTO product = iterator1.next();

            while (it.hasNext()) {

                ProductDTO productFromB = it.next();

                if (product.getItemName().equals(productFromB.getItemName())) {
                    ch = 0;
                    if (product.getPrice() < productFromB.getPrice()) {
                        bestPrices.remove(productFromB);
                        bestPrices.add(product);
                    }
                    if (product.getPrice() == productFromB.getPrice()) {
                        bestPrices.add(product);
                    }
                }


            }
            if (ch == 1)
                bestPrices.add(product);

            ch = 1;
        }


        return bestPrices;
    }


}
