package store.controller;

import java.io.IOException;
import store.model.Product;
import store.util.InventoryManager;
import store.util.StoreManager;
import store.view.InputView;
import store.view.OutputView;

public class Controller {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    StoreManager storeManager;

    public Controller() {
        try {
            storeManager = new StoreManager();
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while (true) {
            printProducts();
            getBuyProducts();
            checkMembership();
            printResult();
            if (!checkAgain()) {
                break;
            }
        }
    }

    private void printProducts() {
        outputView.printProducts(storeManager.getProducts());
    }

    private void getBuyProducts() {
        try {
            String[] input = inputView.readItem();
            for (String product : input) {
                validateBuyItem(product);
                String[] fields = product.substring(1, product.length() - 1).split("-");
                String name = fields[0];
                int count = Integer.parseInt(fields[1]);

                storeManager.buyProduct(name, count);

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            getBuyProducts();
        }
    }

    private void checkMembership() {
        try {
            if (inputView.readApplyMembership()) {
                storeManager.setMembershipDiscount();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            checkMembership();
        }
    }

    private void printResult() {
        storeManager.setAmountInformation();
        outputView.printReceipt(storeManager.getReceipt(), storeManager.getAmountInformation());
    }

    private boolean checkAgain() {
        try {
            if (inputView.readApplyAgain()) {
                storeManager.reset();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return checkAgain();
    }

    private void validateBuyItem(String item) {
        if (item.charAt(0) != '[' || item.charAt(item.length() - 1) != ']') {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

}
