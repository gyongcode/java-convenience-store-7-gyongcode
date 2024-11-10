package store.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("Product 클래스 생성 테스트")
    @Test
    void Product_클래스_생성_테스트() {
        Product product = new Product("콜라", 1000, 10, "null");
        assertThat(product.getName()).isEqualTo("콜라");
        assertThat(product.getPrice()).isEqualTo(1000);
        assertThat(product.getQuantity()).isEqualTo(10);
        assertThat(product.getPromotion()).isEqualTo("null");
    }

    @DisplayName("Product 생성자 예외처리 테스트")
    @Test
    void Product_생성자_예외처리_테스트() {
        assertThatThrownBy(() -> new Product("", 1000, 10, "null")).isInstanceOf(
            IllegalArgumentException.class);
        assertThatThrownBy(() -> new Product(null, 1000, 10, "null")).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> new Product("콜라", -1, 10, "null")).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> new Product("콜라", 1000, -1, "null")).isInstanceOf(
            IllegalArgumentException.class);
    }

    @DisplayName("상품 수량 차감 테스트")
    @Test
    void 상품_수량_차감_테스트() {
        Product product = new Product("콜라", 1000, 10, "null");

        product.deductQuantity(5);
        assertThat(product.getQuantity()).isEqualTo(5);

        assertThatThrownBy(() -> product.deductQuantity(6)).isInstanceOf(
            IllegalArgumentException.class);

        assertThatThrownBy(() -> product.deductQuantity(-1)).isInstanceOf(
            IllegalArgumentException.class);
    }

}