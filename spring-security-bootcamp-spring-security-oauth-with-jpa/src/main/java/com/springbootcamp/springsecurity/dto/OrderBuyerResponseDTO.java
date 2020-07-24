package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.embeddableclass.AddressCopy;

import java.util.Date;
import java.util.List;


public class OrderBuyerResponseDTO {

    private Long ordersId;
    private Float amountPaid;
    private Date dateCreated;
    private String paymentMethod;
    private AddressCopy addressCopy;
    private List<OrderProductSellerResponseDTO> orderProduct;

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public AddressCopy getAddressCopy() {
        return addressCopy;
    }

    public void setAddressCopy(AddressCopy addressCopy) {
        this.addressCopy = addressCopy;
    }

    public List<OrderProductSellerResponseDTO> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(List<OrderProductSellerResponseDTO> orderProduct) {
        this.orderProduct = orderProduct;
    }
}
