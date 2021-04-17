package internship.rest.car.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @GetMapping("/add")
    public ModelAndView addCar() {
        return new ModelAndView("addCar.html");
    }

    @GetMapping("/update")
    public ModelAndView updateCar() {
        return new ModelAndView("updateCar.html");
    }
}
