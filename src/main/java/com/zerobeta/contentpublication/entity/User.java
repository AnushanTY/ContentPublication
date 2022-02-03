package com.zerobeta.contentpublication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "PROFILE_COMPLETED_DATE")
    private Date profileCompletedDate;

    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PROFILE_COMPLETED")
    private Boolean profileCompleted;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @JsonIgnoreProperties("users")
    private Set<Role> userRole = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_SUBSCRIPTION",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    @JsonIgnoreProperties("users")
    private Set<ContentCategory> contentCategories = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetail userDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }

    public Set<ContentCategory> getContentCategories() {
        return contentCategories;
    }

    public void setContentCategories(Set<ContentCategory> contentCategories) {
        this.contentCategories = contentCategories;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Boolean getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(Boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getProfileCompletedDate() {
        return profileCompletedDate;
    }

    public void setProfileCompletedDate(Date profileCompletedDate) {
        this.profileCompletedDate = profileCompletedDate;
    }
}
