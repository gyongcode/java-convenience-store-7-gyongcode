package store.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @DisplayName("Promotion 테스트")
    @Test
    void Promotion_테스트() {
        Promotion promotion = new Promotion("반짝할인", 1, 1, "2024-11-01", "2024-11-30");
        assertThat(promotion.getName()).isEqualTo("반짝할인");
        assertThat(promotion.getBuy()).isEqualTo(1);
        assertThat(promotion.getGet()).isEqualTo(1);
        assertThat(promotion.isPromotionVaild()).isTrue();

        promotion = new Promotion("반짝할인", 1, 1, "2024-11-01", "2024-11-02");
        assertThat(promotion.isPromotionVaild()).isFalse();
    }
}