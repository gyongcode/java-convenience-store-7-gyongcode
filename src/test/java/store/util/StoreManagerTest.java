package store.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import store.model.AmountInformation;
import store.model.Product;
import store.model.Receipt;

class StoreManagerTest {

    @Test
    void 구매로직_테스트() throws IOException {
        StoreManager storeManager = new StoreManager();
        storeManager.buyProduct("사이다", 3);
        storeManager.setAmountInformation();
        storeManager.setMembershipDiscount();
        Receipt receipt = storeManager.getReceipt();
        AmountInformation amountInformation = storeManager.getAmountInformation();
        Product buyProduct = receipt.getBuyPoducts().get(0);
        Product buyPromotion = receipt.getPromotionProducts().get(0);

        assertThat(buyProduct.getName()).isEqualTo("사이다");
        assertThat(buyProduct.getPrice()).isEqualTo(3000);
        assertThat(buyProduct.getQuantity()).isEqualTo(3);

        assertThat(buyPromotion.getName()).isEqualTo("사이다");
        assertThat(buyPromotion.getPrice()).isEqualTo(1000);
        assertThat(buyPromotion.getPrice()).isEqualTo(1);
    }

    @Test
    void 구매수량이_부족() throws IOException {
        StoreManager storeManager = new StoreManager();
        Assertions.assertThatThrownBy(() -> storeManager.buyProduct("사이다", 21))
            .isInstanceOf(IllegalArgumentException.class);
    }
}