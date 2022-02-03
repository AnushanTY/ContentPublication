package com.zerobeta.contentpublication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CONTENT_COMMET")
public class ContentComment {

    @Id
    @Column(name = "CONTENT_COMMET_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTENT_ID")
    @JsonBackReference
    private Content content;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "COMMENT")
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentComment that = (ContentComment) o;
        return id.equals(that.id) && content.equals(that.content) && user.equals(that.user) && comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, user, comment);
    }
}
