package team.components.gateway.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.components.gateway.model.dto.UserDto;
import team.components.gateway.model.entity.User;
import team.components.gateway.model.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User saveUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}
