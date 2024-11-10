package store.model;

import java.util.List;

public class Products {
    private List<Product> products;

    public Products(List<Product> products){
        this.products = products;
    }

    public boolean deductProduct(String name, int count){
        for (Product product : products) {
            if(product.getName().equals(name)){
                if(product.deductQuntity(count)){
                    return true;
                }
            }
        }
    }
}
