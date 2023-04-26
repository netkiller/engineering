package cn.netkiller.domain.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gantt")
public class Gantt implements Serializable {
    public static final long serialVersionUID = 7998903265542138801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false, columnDefinition = "BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT")
    public Long id;
    public String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date finish;
    public String resource;
    public Integer progress = 0;
    public Long predecessor;


    public Boolean milestone = false;
    public Boolean parent = false;

    @ManyToOne
//            (targetEntity = Gantt.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn(name = "parent_id")
    @JoinColumn(name = "parent_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "gantt_has_subgantt"))
    public Gantt gantt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    public Date ctime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'")
    public Date mtime;

    @Column(columnDefinition = "enum('Enabled','Disabled') DEFAULT 'Enabled' COMMENT '状态'")
    public String status;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
//    @OneToMany(targetEntity = Gantt.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Gantt> gantts; // = new HashSet<Gantt>(0);


}
