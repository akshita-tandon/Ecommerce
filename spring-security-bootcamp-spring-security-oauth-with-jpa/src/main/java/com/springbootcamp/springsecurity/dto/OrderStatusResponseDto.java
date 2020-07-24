package com.springbootcamp.springsecurity.dto;


public class OrderStatusResponseDto {

    private Long OrderStatusId;
    private String fromStatus;
    private String toStatus;
    private String transitionNotesComment;
    private Boolean isDeleted;

    public OrderStatusResponseDto() {
    }

    public Long getOrderStatusId() {
        return OrderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        OrderStatusId = orderStatusId;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComment() {
        return transitionNotesComment;
    }

    public void setTransitionNotesComment(String transitionNotesComment) {
        this.transitionNotesComment = transitionNotesComment;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
