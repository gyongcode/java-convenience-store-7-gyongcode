package store.model;

public class Product {
    final static int ZERO = 0;

    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        validateName(name);
        validateUpToZero(price);
        validateUpToZero(quantity);

        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public void deductQuantity(int BuyCount) {
        validateUpToZero(BuyCount);

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

    private void validateUpToZero(int n) {
        if (n <= ZERO) {
            throw new IllegalArgumentException("[ERROR] 0보다 큰 수를 입력하세요");
        }
    }
}
