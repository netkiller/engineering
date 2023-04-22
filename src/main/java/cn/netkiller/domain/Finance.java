package cn.netkiller.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "article")
public class Finance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;
    private String title;
    private String content;
}
