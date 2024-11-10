package store.model;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Promotion {

    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd'T'HH:mm:ss");

    private String name;
    private int buy;
    private int get;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    public Promotion(String name, int buy, int get, String start_date, String end_date) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start_date = LocalDateTime.parse(start_date + "T00:00:00", formatter);
        this.end_date = LocalDateTime.parse(end_date + "T23:59:59", formatter);
    }

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public boolean isPromotionVaild() {
        LocalDateTime now = DateTimes.now();
        if (start_date.isBefore(now) && end_date.isBefore(now)) {
            return true;
        }
        return false;
    }
}
