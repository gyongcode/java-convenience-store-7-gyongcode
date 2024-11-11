package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.model.Product;
import store.model.Promotion;

public class InputView {

    public String[] readItem() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String[] input = Console.readLine().split(",");

        return input;
    }

    public boolean readApplyPromotion(Product product) {
        System.out.println("현재 " + product.getName() + "은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        String input = Console.readLine();
        try {
            validateYN(input);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return readApplyPromotion(product);
        }
        if (input.equals("Y")) {
            return true;
        }
        return false;
    }

    public boolean readNotApplyPromotion(String name, int count) {
        System.out.println(
            "현재 " + name +" "+count+"개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        String input = Console.readLine();
        try {
            validateYN(input);
        }catch (IllegalArgumentException e){
            return readNotApplyPromotion(name, count);
        }
        if (input.equals("Y")) {
            return true;
        }
        return false;
    }

    public boolean readApplyMembership(){
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String input = Console.readLine();
        validateYN(input);

        if (input.equals("Y")) {
            return true;
        }
        return false;
    }

    public boolean readApplyAgain(){
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = Console.readLine();
        validateYN(input);

        if (input.equals("Y")) {
            return true;
        }
        return false;
    }

    private void validateYN(String input){
        if(!input.equals("Y") && !input.equals("N"))
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
    }
}
