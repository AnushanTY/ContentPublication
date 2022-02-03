package com.zerobeta.contentpublication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CONTENT_CATEGORY")
public class ContentCategory {

    @Id
    @Column(name = "CATEGORY_ID")
    private Integer id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @ManyToMany(mappedBy = "contentCategories", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("contentCategories")
    @JsonIgnore
    private Set<User> users = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentCategory that = (ContentCategory) o;
        return id.equals(that.id) && categoryName.equals(that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }
}
