package store.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.util.MDFileReader;
import store.util.Saparator;

public class InventoryManager {

    private static final String FILEPATH = "src/main/resources/products.md";

    private MDFileReader mdFileReader;
    private List<Product> products;

    public InventoryManager() throws IOException {
        this.products = new ArrayList<>();

        readProductsFile();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void readProductsFile() throws IOException {
        mdFileReader = new MDFileReader(FILEPATH);
        List<String> lines = mdFileReader.getLines();

        for (String line : lines) {
            products.add(fromLineToProduct(line));
        }
    }

    private Product fromLineToProduct(String line) {
        String[] fields = line.split(",");
        String name = fields[0];
        int price = Integer.parseInt(fields[1]);
        int quantity = Integer.parseInt(fields[2]);
        String promotion = fields[3];

        return new Product(name, price, quantity, promotion);
    }

    public void deductProduct(String name, int buyCount, String promotion) {
        Product product = findProductByNameAndPromotion(name, promotion);
        product.deductQuantity(buyCount);
    }

    public Product findProductByNameAndPromotion(String name, String promotion) {
        for (Product product : products) {
            if (product.getName().equals(name) && product.getPromotion().equals(promotion)) {
                return product;
            }
        }

        throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
    }
}
