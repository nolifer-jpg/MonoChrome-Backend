package com.monochrome.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.createdAt = Instant.now();
    }

    protected Task() {}

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    //Domain Method
    public void markCompleted() {
        this.completed = true;
    }
}
