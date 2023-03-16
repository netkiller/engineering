package cn.netkiller.controller.user;

import cn.netkiller.domain.Article;
import cn.netkiller.domain.User;
import cn.netkiller.repository.ArticleRepository;
import cn.netkiller.repository.UserRepository;
import cn.netkiller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//public class UserRestController {
public record UserRestController(UserService userService, UserRepository userRepository,
                                 ArticleRepository articleRepository) {
//    @Autowired
//    UserService userService = new UserService(userRepository);

    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Helloworld!!!");
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

//    @GetMapping("/test")
//    public ResponseEntity<Iterable<Article>> test() {
//        User user = new User("neo", "123456", "neo chen");
//        System.out.println(user.toString());
////        userRepository.save(user);
//        userService.save(user);
////        userService.findAll()
////        System.out.println(userRepository.findAll().toString());
//        articleRepository.save(new Article("Neo", "Chen"));
//        for (Article article : articleRepository.findAll()) {
//            System.out.println(article);
//        }
//        return ResponseEntity.ok(articleRepository.findAll());
//    }

    @GetMapping("/test")
    public ResponseEntity<Iterable<User>> test() {
        User user = new User("neo", "123456", "neo chen");
        System.out.println(user.toString());
        userRepository.save(user);
//        userService.save(user);
//        userService.findAll()
//        System.out.println(userRepository.findAll().toString());

        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<User>> findByLastName(String name) {
        return ResponseEntity.ok(userService.findByName(name));
    }
}
