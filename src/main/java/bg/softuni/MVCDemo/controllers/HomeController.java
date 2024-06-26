package bg.softuni.MVCDemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/home")
    public String home(@RequestParam(value = "show-ad", required = false) Integer adId) {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }
}
