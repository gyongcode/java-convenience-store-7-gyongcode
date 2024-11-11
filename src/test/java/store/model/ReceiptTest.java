package store.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ReceiptTest {

    @Test
    void Receipt_테스트() {
        Receipt receipt = new Receipt();
        receipt.addBuyProdudct(new Product("TEST1", 3000, 3, "null"));
        receipt.addPromotionProdudct(new Product("TEST2", 1000, 1, "null"));

        Product buyProduct = receipt.getBuyPoducts().getFirst();
        Product promodionProduct = receipt.getPromotionProducts().getFirst();

        Assertions.assertThat(buyProduct.getName()).isEqualTo("TEST1");
        Assertions.assertThat(buyProduct.getPrice()).isEqualTo(3000);
        Assertions.assertThat(buyProduct.getQuantity()).isEqualTo(3);

        Assertions.assertThat(promodionProduct.getName()).isEqualTo("TEST2");
        Assertions.assertThat(promodionProduct.getPrice()).isEqualTo(1000);
        Assertions.assertThat(promodionProduct.getQuantity()).isEqualTo(1);

    }
}