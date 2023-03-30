package cn.netkiller.domain.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Slf4j
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project implements Serializable {
    public static final long serialVersionUID = 7998903421265538801L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    public Long id;
    public String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date finish;
    public String resource;
    //    public Long parent;
    public Long predecessor;

    @Column(columnDefinition = "enum('Enabled','Disabled') DEFAULT 'Enabled' COMMENT '状态'")
    public String status;

    public Boolean milestone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    public Date ctime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'")
    public Date mtime;


    @ManyToOne(targetEntity = Project.class, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parent_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "project_has_subproject"))
    public Project project;

    //    @JsonIgnore
//    @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public Set<Project> projects;
    public Project() {
    }

    public Project(String name, Date start, Date finish, String resource) {
        this.name = name;
        this.start = start;
        this.finish = finish;
        this.resource = resource;
//        this.next = next;
    }
}
