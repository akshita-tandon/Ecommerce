package com.springbootcamp.springsecurity.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class UserAttempts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private Integer attempts;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastmodified")
    private Date lastModified;

    public UserAttempts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "UserAttempts{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", attempts=" + attempts +
                ", lastmodified=" + lastModified +
                '}';
    }
}
