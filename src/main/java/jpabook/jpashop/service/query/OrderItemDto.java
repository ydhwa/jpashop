package jpabook.jpashop.service.query;

import jpabook.jpashop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private final String itemName;      // 상품명
    private final int orderPrice;       // 주문가격
    private final int count;            // 주문수량

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
