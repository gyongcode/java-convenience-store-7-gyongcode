package store.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private List<Product> buyPoducts;
    private List<Product> promotionProducts;


    public Receipt() {
        buyPoducts = new ArrayList<>();
        promotionProducts = new ArrayList<>();
    }

    public List<Product> getBuyPoducts() {
        return buyPoducts;
    }

    public List<Product> getPromotionProducts() {
        return promotionProducts;
    }

    public String toStringBuyProducts() {
        String string = "";
        for (Product product : buyPoducts) {
            string +=
                product.getName() + "\t\t" + product.getQuantity() + "\t" + String.format("%,d",
                    product.getPrice())
                    + "\n";
        }

        return string;
    }

    public String toStringPromotionProducts() {
        String string = "";
        for (Product product : promotionProducts) {
            string += product.getName() + "\t\t" + product.getQuantity() + "\n";
        }

        return string;
    }

    public void addBuyProdudct(Product product) {
        buyPoducts.add(product);
    }

    public void addPromotionProdudct(Product product) {
        promotionProducts.add(product);
    }
}
