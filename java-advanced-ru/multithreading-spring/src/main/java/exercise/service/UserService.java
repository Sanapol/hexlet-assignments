package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> show(long id) {
        Mono<User> user = userRepository.findById(id);
        return user;
    }

    public Mono<User> update(long id, User data) {
        data.setId(id);
        return userRepository.save(data);
    }

    public Mono<Void> delete(long id) {
        return userRepository.deleteById(id);
    }
    // END
}
