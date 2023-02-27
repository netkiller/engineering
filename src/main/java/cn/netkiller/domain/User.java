package cn.netkiller.domain;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "article")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;
    private String username;
    private String password;
    private String name;
}
