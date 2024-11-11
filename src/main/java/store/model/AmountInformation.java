package store.model;

import java.util.List;

public class AmountInformation {

    private Receipt receipt;
    private int totalPurchaseAmount = 0;
    private int totalPromotionDiscount = 0;
    private int totalCount = 0;
    private int amountWithtoutDiscount = 0;
    private int membershipDiscount = 0;
    private int finalAmount = 0;

    public AmountInformation(Receipt receipt) {
        this.receipt = receipt;
    }

    public void addAmountWithoutDiscount(int price) {
        this.amountWithtoutDiscount += price;
    }

    public void calculateInformation() {
        calculatePurchaseAmount();
        calculatePromotionDiscount();
        calculateFinalAmount();
    }

    public void calculateMembershipDiscount() {
        this.membershipDiscount = amountWithtoutDiscount / 100 * 30;
        if (this.membershipDiscount > 8000) {
            this.membershipDiscount = 8000;
        }
    }

    private void calculatePurchaseAmount() {
        List<Product> products = receipt.getBuyPoducts();
        for (Product product : products) {
            totalPurchaseAmount += product.getPrice();
            totalCount += product.getQuantity();
        }
    }

    private void calculatePromotionDiscount() {
        List<Product> products = receipt.getPromotionProducts();
        for (Product product : products) {
            totalPromotionDiscount += product.getPrice();
        }
    }

    private void calculateFinalAmount() {
        finalAmount = totalPurchaseAmount - totalPromotionDiscount - membershipDiscount;
    }

    public String toString() {
        String string = "";

        string += "총구매액\t\t" + totalCount + "\t" + String.format("%,d", totalPurchaseAmount) + "\n";
        string += "행사할인\t\t\t-" + String.format("%,d", totalPromotionDiscount) + "\n";
        string += "멤버십할인\t\t\t-" + String.format("%,d", membershipDiscount) + "\n";
        string += "내실돈\t\t\t" + String.format("%,d", finalAmount);

        return string;
    }
}
