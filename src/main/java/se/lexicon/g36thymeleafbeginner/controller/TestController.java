package se.lexicon.g36thymeleafbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping("/greeting") // == http://localhost:8080/greeting
    public String helloWorld(Model model){
        String message = "Hello Java group 36";
        model.addAttribute("message", message);

        return "greeting";
    }

    @GetMapping("/greeting2")
    public ModelAndView greeting2(){
        String message = "Hello Java group 36";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        modelAndView.setViewName("greeting");
        return modelAndView;
    }

}
