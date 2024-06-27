package bg.softuni.MVCDemo.services;

import bg.softuni.MVCDemo.dtos.UserLoginDto;
import bg.softuni.MVCDemo.dtos.UserRegisterDto;
import bg.softuni.MVCDemo.entities.User;
import bg.softuni.MVCDemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(UserRegisterDto registerDto) {
        boolean exists = userRepository.existsByEmail(registerDto.getEmail());

        if (exists || !registerDto.getConfirmPassword().equals(registerDto.getPassword())) return false;

        User user = new User(registerDto);
        this.userRepository.saveAndFlush(user);

        return true;
    }

    public boolean login(UserLoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());

        return optionalUser.isPresent() &&
                optionalUser.get()
                        .getPassword()
                        .equals(loginDto.getPassword());
    }
}
