package com.zerobeta.contentpublication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "CONTENT")
public class Content {

    @Id
    @Column(name = "CONTENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "PUBLISH_DATE")
    private Date publishedDate;

    @Column(name = "EDIT_DATE")
    private Date editDate;

    @Column(name = "IS_PUBLISHED")
    private Boolean isPublished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "content")
    @JsonManagedReference
    private Set<ContentComment> contentComments = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private ContentCategory contentCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ContentComment> getContentComments() {
        return contentComments;
    }

    public void setContentComments(Set<ContentComment> contentComments) {
        this.contentComments = contentComments;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }

    public void setContentCategory(ContentCategory contentCategory) {
        this.contentCategory = contentCategory;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean published) {
        isPublished = published;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
