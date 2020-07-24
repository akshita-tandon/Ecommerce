package com.springbootcamp.springsecurity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingInfo<String> {

    @CreatedBy
    protected java.lang.String createdBy;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    protected java.util.Date createdDate;

    @LastModifiedBy
    protected java.lang.String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected java.util.Date lastModifiedDate;

    public java.lang.String getCreatedBy() {
        return (java.lang.String) createdBy;
    }

    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }

    public java.util.Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public java.lang.String getLastModifiedBy() {
        return (java.lang.String) lastModifiedBy;
    }

    public void setLastModifiedBy(java.lang.String lastModifiedBy) {
        this.lastModifiedBy = (java.lang.String) lastModifiedBy;
    }

    public java.util.Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;


    }
}
