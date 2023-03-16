//package cn.netkiller.domain.test;
//
//import java.util.Date;
//import java.util.Set;
//
//
//import jakarta.persistence.*;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
//    public int id;
//    public String name;
//    public String description;
//    public String path;
//
//    @Column(columnDefinition = "enum('Enabled','Disabled') DEFAULT 'Enabled' COMMENT '状态'")
//    public String status;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
//    public Date ctime;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间'")
//    public Date mtime;
//
//    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
//    @JoinColumn(name = "pid", referencedColumnName = "id")
//    private Category categorys;
//
//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
//    private Set<Category> category;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public Date getCtime() {
//        return ctime;
//    }
//
//    public void setCtime(Date ctime) {
//        this.ctime = ctime;
//    }
//
//    public Date getMtime() {
//        return mtime;
//    }
//
//    public void setMtime(Date mtime) {
//        this.mtime = mtime;
//    }
//
//    public Category getCategorys() {
//        return categorys;
//    }
//
//    public void setCategorys(Category categorys) {
//        this.categorys = categorys;
//    }
//
//    public Set<Category> getCategory() {
//        return category;
//    }
//
//    public void setCategory(Set<Category> category) {
//        this.category = category;
//    }
//
//    @Override
//    public String toString() {
//        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", path=" + path + ", status="
//                + status + ", ctime=" + ctime + ", mtime=" + mtime + ", categorys=" + categorys + ", category="
//                + category + "]";
//    }
//
//}
//
