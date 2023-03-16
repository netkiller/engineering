package cn.netkiller.record;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public record Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
        Long id,
        String name

) {
}
