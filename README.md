






# java-convenience-store-precourse

# 편의점

---

## 입출력

#### 예외처리

---



## 재고 관리
- products.md에서 재고 정보를 가져온다.
- 각 상품을 Product 클래스 구현
- 재고 관리를 하는 InventoryManager 구현
- 고객이 상품을 구매할 때 마다 수량을 재고에서 차감한다.

#### 예외 처리
- Product
  - product를 생성할 때 이름이 null 이거나 ""이면 예외 발생
  - product를 생성할 때 가격이 0이하면 예외 발생
  - product를 생성할 때 수량이 0이하면 예외 발생
  - 재고에서 차감할 때 수량보다 작으면 flase를 리턴
  - 재고에서 차감할 때 차감량이 0보다 작으면 예외 발생

- ProductManager
  

---


## 프로모션 할인
- promotion.md에서 할인 정보를 가져온다.
- 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
- 프로모션은 N개 구매 시 1개 무료증정(Buy N Get 1 Free)의 형태로 진행된다.
- 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 여러 프로모션이 적용되지 않는다.
- 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.
- 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감하며, 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.
- 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
- 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내한다.


#### 예외 처리

---

## 멤버십 할인
- 멤버십 회원은 프로모션 미적용 금액의 30%를 할인 받는다.
  - 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
  - 멤버십 할인의 최대 한도는 8,000원이다.

#### 예외 처리

---


## 영수증 출력
- 영수증은 고객의 구매 내역과 할인을 요약하여 출력한다.
- 영수증 항목은 아래와 같다.
  - 구매 상품 내역: 구매한 상품명, 수량, 가격
  - 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품의 목록

#### 예외 처리

---

## 금액 정보

- 총구매액: 구매한 상품의 총 수량과 총 금액
- 행사할인: 프로모션에 의해 할인된 금액
- 멤버십할인: 멤버십에 의해 추가로 할인된 금액
- 내실돈: 최종 결제 금액
- 영수증의 구성 요소를 보기 좋게 정렬하여 고객이 쉽게 금액과 수량을 확인할 수 있게 한다.

#### 예외 처리