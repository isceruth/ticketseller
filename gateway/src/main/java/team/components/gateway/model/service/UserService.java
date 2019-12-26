package team.components.gateway.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.components.gateway.model.dto.UserDto;
import team.components.gateway.model.entity.User;
import team.components.gateway.model.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public User findOne(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));
    }
}
