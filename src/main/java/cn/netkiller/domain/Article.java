package cn.netkiller.domain;

import jakarta.persistence.*;

import java.io.Serializable;



@Entity
@Table(name = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 7998903421265538801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;
    private String title;
    private String content;

    public Article() {

    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
    }

}

