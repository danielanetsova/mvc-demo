package bg.softuni.MVCDemo.controllers;

import bg.softuni.MVCDemo.dtos.UserLoginDto;
import bg.softuni.MVCDemo.dtos.UserRegisterDto;
import bg.softuni.MVCDemo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    public ModelAndView doLogin(@Valid UserLoginDto loginDto, BindingResult bindingResult) {
        if (loginDto.getUsername().equals("admin") && loginDto.getPassword().equals("secure")) {

            ModelAndView result = new ModelAndView();
            result.setViewName("redirect:/home");
            return result;
        }

        List<String> errors = bindingResult
                .getAllErrors()
                .stream()
                .map(error -> error.getObjectName() + " " + error.getDefaultMessage())
                .toList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        modelAndView.addObject("errors", errors);

        return modelAndView;
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDto registerDto) {
        boolean successfulRegistration = userService.register(registerDto);

        if (successfulRegistration) {
            return "redirect: /users/login";
        }

        return "user/register";
    }

    @GetMapping("/info/{id}")
    public String getUserInfo(@PathVariable("id") long id) {
        return "home";
    }
}
