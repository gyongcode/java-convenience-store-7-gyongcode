package store.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.model.AmountInformation;
import store.model.Product;
import store.model.Promotion;
import store.model.Receipt;
import store.view.InputView;

public class StoreManager {

    private Receipt receipt = new Receipt();
    private AmountInformation amountInformation = new AmountInformation(receipt);
    private InputView inputView = new InputView();
    private PromotionManager promotionManager;
    private InventoryManager inventoryManager;

    public StoreManager() throws IOException {
        promotionManager = new PromotionManager();
        inventoryManager = new InventoryManager();
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public AmountInformation getAmountInformation() {
        return amountInformation;
    }

    public void buyProduct(String name, int buyCount) {
        List<Product> products = inventoryManager.findProductsByName(name);
        checkQuantity(products, buyCount);

        if (products.size() >= 2) {
            checkPromotionDate(products);
        }
        if (products.size() >= 2) {
            buyPromotion(products.get(0), products.get(1), buyCount);
            return;
        }
        Product product = products.get(0);
        buyNormal(product, buyCount);
    }

    private void buyPromotion(Product promotionProduct, Product normalProduct, int buyCount) {
        String name = promotionProduct.getName();
        int price = promotionProduct.getPrice();
        int buy = promotionManager.findByName(promotionProduct.getPromotion()).getBuy();
        int quantity = promotionProduct.getQuantity();

        if (buyCount < quantity) {
            if (buyCount % (buy + 1) == 1) {
                if (inputView.readApplyPromotion(promotionProduct)) {
                    buyCount++;
                }
            }
            promotionProduct.deductQuantity(buyCount);
            int promotionCount = buyCount / (buy + 1);

            int amountWithoutDiscount = (buyCount - promotionCount * (buy + 1)) * price;
            amountInformation.addAmountWithoutDiscount(amountWithoutDiscount);
            receipt.addPromotionProdudct(
                new Product(name, promotionCount * price, promotionCount, "null"));
            receipt.addBuyProdudct(new Product(name, buyCount * price, buyCount, "null"));
            return;
        }

        if (buyCount >= quantity) {
            int promotionCount = quantity / (buy + 1);

            int amountWithoutDiscount = (buyCount - promotionCount * (buy + 1)) * price;
            int notPromotionCount = quantity - promotionCount * (buy + 1);
            notPromotionCount += buyCount - quantity;

            if (notPromotionCount != 0) {
                if (!inputView.readNotApplyPromotion(name, notPromotionCount)) {
                    buyCount -= notPromotionCount;
                    receipt.addPromotionProdudct(
                        new Product(name, promotionCount * price, promotionCount, "null"));
                    receipt.addBuyProdudct(new Product(name, buyCount * price, buyCount, "null"));
                    return;
                }
            }
            amountInformation.addAmountWithoutDiscount(amountWithoutDiscount);
            receipt.addPromotionProdudct(
                new Product(name, promotionCount * price, promotionCount, "null"));
            receipt.addBuyProdudct(new Product(name, buyCount * price, buyCount, "null"));
        }
    }

    private void buyNormal(Product product, int buyCount) {
        product.deductQuantity(buyCount);
        Product buyProduct = new Product(product.getName(), product.getPrice() * buyCount, buyCount,
            "null");
        amountInformation.addAmountWithoutDiscount(buyProduct.getPrice());
        receipt.addBuyProdudct(buyProduct);
    }

    private void checkPromotionDate(List<Product> products) {
        if (!promotionManager.findByName(products.get(0).getPromotion()).isPromotionVaild()) {
            products.removeFirst();
        }
    }

    public void checkQuantity(List<Product> products, int buyCount) {
        int totalQuantity = 0;

        for (Product product : products) {
            totalQuantity += product.getQuantity();
        }

        if (totalQuantity < buyCount) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public void setMembershipDiscount() {
        amountInformation.calculateMembershipDiscount();
    }

    public void setAmountInformation() {
        amountInformation.calculateInformation();
    }

    public List<Product> getProducts() {
        return inventoryManager.getProducts();
    }

    public void reset() {
        this.receipt = new Receipt();
        this.amountInformation = new AmountInformation(receipt);
    }

    public Promotion getPromotion(String name) {
        return promotionManager.findByName(name);
    }
}
