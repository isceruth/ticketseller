package team.components.cinema.service;

import org.springframework.stereotype.Service;
import team.components.cinema.model.User;
import team.components.cinema.repository.UserRepository;

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
