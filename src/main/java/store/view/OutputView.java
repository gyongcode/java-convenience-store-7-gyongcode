package store.view;

import java.util.List;
import store.model.AmountInformation;
import store.model.Product;
import store.model.Receipt;

public class OutputView {

    public void printProducts(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.\n");

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void printReceipt(Receipt receipt, AmountInformation amountInformation) {
        System.out.println("==============W 편의점================");

        System.out.println("상품명\t\t수량\t금액");
        System.out.println(receipt.toStringBuyProducts());

        System.out.println("=============증\t정===============");
        System.out.println(receipt.toStringPromotionProducts());

        System.out.println("====================================");
        System.out.println(amountInformation.toString());
    }

}
