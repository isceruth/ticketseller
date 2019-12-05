package team.components.cinema.model.service;

import org.springframework.stereotype.Service;
import team.components.cinema.model.repository.UserRepository;
import team.components.cinema.model.entity.User;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
