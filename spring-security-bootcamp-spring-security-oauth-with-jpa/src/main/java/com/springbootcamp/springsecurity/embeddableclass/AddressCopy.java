package com.springbootcamp.springsecurity.embeddableclass;

import com.springbootcamp.springsecurity.entity.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class AddressCopy {

    private String city;
    private String state;
    private String address;
    private Integer zip_code;
    private String label;

    public AddressCopy(Address orderAddress){

        this.city=orderAddress.getCity();
        this.state=orderAddress.getState();
        this.address=orderAddress.getAddress();
        this.zip_code=orderAddress.getZipCode();
        this.label=orderAddress.getLabel();
    }

    public AddressCopy() {
    }

}
