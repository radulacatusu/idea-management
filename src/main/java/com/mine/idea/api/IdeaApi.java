package com.mine.idea.api;

import javax.validation.constraints.NotNull;

/**
 * @stefanl
 */
public class IdeaApi {
    private long id;
    @NotNull
    private String content;
    @NotNull
    private Integer impact;
    @NotNull
    private Integer ease;
    @NotNull
    private Integer confidence;
    private long createdAt;
    private Double averageScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "IdeaApi{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", impact=" + impact +
                ", ease=" + ease +
                ", confidence=" + confidence +
                '}';
    }
}
