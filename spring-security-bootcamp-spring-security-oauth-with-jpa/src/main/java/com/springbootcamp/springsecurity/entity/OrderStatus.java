package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springbootcamp.springsecurity.AuditingInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderStatusId")

public class OrderStatus implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderStatusId;
    @MapsId
    @OneToOne
    @JoinColumn(name = "order_product_id")
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status")
    private FROM_STATUS fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status")
    private TO_STATUS toStatus;

    @Column(name = "transition_notes_comment")
    private String transitionNotesComment;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    public OrderStatus() {
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public FROM_STATUS getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(FROM_STATUS fromStatus) {
        this.fromStatus = fromStatus;
    }

    public TO_STATUS getToStatus() {
        return toStatus;
    }

    public void setToStatus(TO_STATUS toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComment() {
        return transitionNotesComment;
    }

    public void setTransitionNotesComment(String transitionNotesComment) {
        this.transitionNotesComment = transitionNotesComment;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "order_status_id=" + orderStatusId +
                ", orderProduct=" + orderProduct +
                ", from_status=" + fromStatus +
                ", to_status=" + toStatus +
                ", transition_notes_comment='" + transitionNotesComment + '\'' +
                ", is_deleted=" + isDeleted +
                '}';
    }
}
