package store.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InventoryManagerTest {

    @DisplayName("파일 읽어서 Prodcuts로 저장")
    @Test
    void 파일_읽어서_Prodcuts로_저장() throws IOException {
        InventoryManager inventoryManager = new InventoryManager();

        assertThat(inventoryManager.getProducts().size()).isEqualTo(16);

        Product product = inventoryManager.findProductByNameAndPromotion("콜라", "null");

        assertThat(product.getName()).isEqualTo("콜라");
        assertThat(product.getPrice()).isEqualTo(1000);
        assertThat(product.getQuantity()).isEqualTo(10);
        assertThat(product.getPromotion()).isEqualTo("null");

        Assertions.assertThatThrownBy(
                () -> inventoryManager.findProductByNameAndPromotion("empty", "null"))
            .isInstanceOf(IllegalArgumentException.class);

    }
}