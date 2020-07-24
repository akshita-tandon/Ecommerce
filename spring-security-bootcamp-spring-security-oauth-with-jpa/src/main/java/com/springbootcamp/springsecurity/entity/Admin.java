package com.springbootcamp.springsecurity.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    public Admin() {
    }

    public Boolean getNonlocked() {
        return nonlocked;
    }

    public void setNonlocked(Boolean nonlocked) {
        this.nonlocked = nonlocked;
    }

}
