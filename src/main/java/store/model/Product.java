package store.model;

public class Product implements Comparable<Product> {

    final static int ZERO = 0;

    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        validateName(name);
        validateNotNegative(price);
        validateNotNegative(quantity);

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    public String toString() {
        String string = "- " + name + " " + String.format("%,d", price) + "원 ";
        if (quantity == 0) {
            string += "재고 없음 ";
        }

        if (quantity > 0) {
            string += quantity + "개 ";
        }

        if (promotion.equals("null")) {
            return string;
        }
        return string += promotion;
    }

    public void deductQuantity(int BuyCount) {
        validateNotNegative(BuyCount);

        if (quantity >= BuyCount) {
            quantity -= BuyCount;
            return;
        }

        throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }

    private void validateName(String name) {
        if (name == null || name == "") {
            throw new IllegalArgumentException("[ERROR] 상품의 이름을 잘못 입력하였습니다.");
        }
    }

    private void validateNotNegative(int n) {
        if (n < ZERO) {
            throw new IllegalArgumentException("[ERROR] 0보다 크거나 같은 수를 입력하세요");
        }
    }

    @Override
    public int compareTo(Product o) {
        if (this.promotion.equals("null")) {
            return 1;
        }
        if (o.promotion.equals("null")) {
            return -1;
        }
        return 0;
    }
}
