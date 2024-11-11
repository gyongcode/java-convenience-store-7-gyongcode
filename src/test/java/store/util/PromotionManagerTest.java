package store.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import store.model.Promotion;

class PromotionManagerTest {

    @Test
    void 파일을_잘_읽어오는지_테스트() throws IOException {
        PromotionManager promotionManager = new PromotionManager();

        Promotion promotion = promotionManager.findByName("탄산2+1");
        Assertions.assertThat(promotion.getBuy()).isEqualTo(2);
        Assertions.assertThat(promotion.getGet()).isEqualTo(1);

        Assertions.assertThatThrownBy(() -> promotionManager.findByName("없는거"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}