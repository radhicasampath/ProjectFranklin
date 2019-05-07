package com.practice.dbops.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(OrderItem.OrderItemPK.class)
@Table(name="order_items")
public class OrderItem {

    @Embeddable
    @Data
    static class OrderItemPK implements Serializable {
        private Long orderId;
        private Long itemId;
    }

    @EmbeddedId
    private OrderItemPK orderItemPK;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    })
    Order order;

}
