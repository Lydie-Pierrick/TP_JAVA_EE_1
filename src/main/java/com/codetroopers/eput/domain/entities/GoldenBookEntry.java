package com.codetroopers.eput.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "goldenBookEntry")
public class GoldenBookEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private int note;
//    @ElementCollection
//    private List<String> tags = new ArrayList<>();

    public GoldenBookEntry() {
        this.createdAt = new Date();
    }

    public GoldenBookEntry(final String author, final String content) {
        this(author, content, new Date(), 0);
    }

    public GoldenBookEntry(final String author, final String content, final Date createdAt, final int note) {
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.note = note;
    }

    /************************** GETTER / SETTERS ****************************/
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
