package store.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmountInformationTest {

    @Test
    void 금액정보_테스트() {
        Receipt receipt = new Receipt();

        receipt.addBuyProdudct(new Product("콜라", 3000, 3, "null"));
        receipt.addBuyProdudct(new Product("사이다", 3000, 3, "null"));
        receipt.addPromotionProdudct(new Product("콜라", 1000, 1, "null"));
        receipt.addPromotionProdudct(new Product("사이다", 1000, 1, "null"));

        AmountInformation amountInformation = new AmountInformation(receipt);
        amountInformation.addAmountWithoutDiscount(0);

        amountInformation.calculateInformation();
        amountInformation.calculateMembershipDiscount();

        org.assertj.core.api.Assertions.assertThat(amountInformation.toString())
            .isEqualTo("총구매액\t\t6\t6,000\n"
                + "행사할인\t\t\t-2,000\n"
                + "멤버십할인\t\t\t0\n"
                + "내실돈\t\t\t4,000");
    }
}