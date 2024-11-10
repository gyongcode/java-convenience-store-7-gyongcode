package store.model;

public class Product {
    final static int ZERO = 0;

    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        validateName(name);
        validatePrice(price);
        validateQuantity(quantity);

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        if (promotion.equals("null")) {
            this.promotion = null;
            return;
        }
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice(){
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion(){
        return promotion;
    }

    public boolean deductQuantity(int count) {
        validateQuantity(count);

        if (quantity >= count) {
            quantity -= count;
            return true;
        }
        return false;
    }

    private void validateName(String name) {
        if (name == null || name == "") {
            throw new IllegalArgumentException("[ERROR] 상품의 이름을 잘못 입력하였습니다.");
        }
    }

    private void validatePrice(int price) {
        if (price <= ZERO) {
            throw new IllegalArgumentException("[ERROR] 상품의 가격을 잘못 입력하였습니다.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= ZERO) {
            throw new IllegalArgumentException("[ERROR] 상품의 수량을 잘못 입력하였습니다.");
        }
    }
}
