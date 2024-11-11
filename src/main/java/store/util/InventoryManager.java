package store.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import store.model.Product;
import store.model.Promotion;

public class InventoryManager {

    private static final String FILEPATH = "src/main/resources/products.md";

    private MDFileReader mdFileReader;
    private List<Product> products;
    private PromotionManager promotionManager = new PromotionManager();

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


    public List<Product> findProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        for (Product product : this.products) {
            if (product.getName().equals(name)) {
                products.add(product);
            }
        }

        validateProductsNull(products);

        Collections.sort(products);
        return products;
    }

    public void validateProductsNull(List<Product> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }
}
