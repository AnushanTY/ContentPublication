package com.zerobeta.contentpublication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "USER_DETAIL")
public class UserDetail {

    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    @JsonBackReference
    @MapsId
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String descriptions;

    @Column(name = "COUNTRY")
    private String country;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
