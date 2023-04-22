package cn.netkiller.domain.cms;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "文章")
public class Article implements Serializable {
    private static final long serialVersionUID = 7998903421265538801L;

    @Id
    @Column(name = "id", columnDefinition = "BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT")
    private Long id;

    @Column(name = "标题")
    private String title;
    @Column(name = "内容")
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

