package store.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.model.Product;
import store.model.Promotion;

public class PromotionManager {

    private static final String FILEPATH = "src/main/resources/promotions.md";

    private MDFileReader mdFileReader;
    private List<Promotion> promotions;

    public PromotionManager() throws IOException {
        this.promotions = new ArrayList<>();

        readPromotionsFile();
    }


    public void readPromotionsFile() throws IOException {
        mdFileReader = new MDFileReader(FILEPATH);
        List<String> lines = mdFileReader.getLines();

        for (String line : lines) {
            promotions.add(fromLineToPromotion(line));
        }


    }

    private Promotion fromLineToPromotion(String line) {
        String[] fields = line.split(",");
        String name = fields[0];
        int buy = Integer.parseInt(fields[1]);
        int get = Integer.parseInt(fields[2]);
        String start_date = fields[3];
        String end_date = fields[4];

        return new Promotion(name, buy, get, start_date, end_date);
    }

    public Promotion findByName(String name) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }

        throw new IllegalArgumentException("[ERROR] 존재하지 않는 프로모션입니다. 다시 입력해 주세요.");
    }
}
