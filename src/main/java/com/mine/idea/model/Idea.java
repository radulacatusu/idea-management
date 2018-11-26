package com.mine.idea.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @stefanl
 */
@Entity
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private Integer impact;
    private Integer ease;
    private Integer confidence;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getEase() {
        return ease;
    }

    public void setEase(Integer ease) {
        this.ease = ease;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Idea{" +
                "id=" + id +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", impact=" + impact +
                ", ease=" + ease +
                ", confidence=" + confidence +
                ", createdAt=" + createdAt +
                '}';
    }
}
