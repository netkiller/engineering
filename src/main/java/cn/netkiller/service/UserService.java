package cn.netkiller.service;

import cn.netkiller.domain.User;
import cn.netkiller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(UserRepository userRepository) {
    public User save(User user) {
//        User user = new User(user.name(), user.username(),user.password());
        return userRepository.save(user);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
