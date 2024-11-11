package store.model;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.util.InventoryManager;

class InventoryManagerTest {

    @DisplayName("파일 읽어서 Prodcuts로 저장")
    @Test
    void 파일_읽어서_Prodcuts로_저장() throws IOException {
        InventoryManager inventoryManager = new InventoryManager();

        assertThat(inventoryManager.getProducts().size()).isEqualTo(18);

        List<Product> products = inventoryManager.findProductsByName("물");
        Product product = products.get(0);
        assertThat(product.getName()).isEqualTo("물");
        assertThat(product.getPrice()).isEqualTo(500);
        assertThat(product.getQuantity()).isEqualTo(10);
        assertThat(product.getPromotion()).isEqualTo("null");

        Assertions.assertThatThrownBy(
                () -> inventoryManager.findProductsByName("empty"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정렬_테스트() throws IOException {
        InventoryManager inventoryManager = new InventoryManager();

        List<Product> products = inventoryManager.findProductsByName("사이다");
        assertThat(products.get(0).getPromotion()).isEqualTo("탄산2+1");
        assertThat(products.get(1).getPromotion()).isEqualTo("null");
    }
}