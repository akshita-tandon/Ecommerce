package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootcamp.springsecurity.AuditingInfo;
import com.springbootcamp.springsecurity.embeddableclass.AddressCopy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Orders{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long ordersId;
    @NotNull
    @Column(name = "amount_paid")
    private Float amountPaid;
    @NotNull
    @Temporal(value = TemporalType.DATE)
    @Column(name = "date_created")
    private Date dateCreated;
    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;
    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @Embedded
    private AddressCopy addressCopy;

    @JsonIgnore
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<OrderProduct> orderProduct;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private Buyer buyer;

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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public AddressCopy getAddressCopy() {
        return addressCopy;
    }

    public void setAddressCopy(AddressCopy addressCopy) {
        this.addressCopy = addressCopy;
    }

    public Set<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Set<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", amountPaid=" + amountPaid +
                ", dateCreated=" + dateCreated +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", isDeleted=" + isDeleted +
                ", addressCopy=" + addressCopy +
                ", buyer=" + buyer +
                '}';
    }
}
