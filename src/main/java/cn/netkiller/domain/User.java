package cn.netkiller.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
//@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7998903421265538801L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
    Long id;
    String username;
    String password;
    String name;

    public User() {

    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//public record User(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = false)
//        Long id,
//        String username,
//        String password,
//        String name
//) {
////    public User() {
////    }
//
//    public User(String username, String password,String name) {
//        this(username, password,name);
//    }
//}
