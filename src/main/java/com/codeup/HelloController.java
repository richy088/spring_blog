package com.codeup;

//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//    class HelloController {
//        @GetMapping("/hello")
//        @ResponseBody
//        public String hello() {
//            return "Hello from Spring!";
//        }
//    @GetMapping("/hello/{name}")
//    @ResponseBody
//    public String sayHello(@PathVariable String name) {
//        return "Hello " + name + "!";
//    }
//    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
//    @ResponseBody
//    public String addOne(@PathVariable int number) {
//        return number + " plus one is " + (number + 1) + "!";
//    }
//    @GetMapping("/hello/{name}")
//    public String sayHello(@PathVariable String name, Model model) {
//        model.addAttribute("name", name);
//        return "hello";
//    }
//    }

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;

@Controller
public class HelloController {

    @GetMapping("/home")
    public String welcome() {
        return "home";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }

    @GetMapping("/roll-dice")
    public String showDiceRoll() {
        return "dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable(name="n") int n, Model model) {
        Random random = new Random();
        int x = random.nextInt(6) + 1;
//        minValue + rn.nextInt(maxValue - minValue + 1)
        model.addAttribute("x", x);
        model.addAttribute("n", n);
        String message;
        if (n == x) {
           message = "You guessed correct";
        } else {
            message = "You guessed wrong";
        }
        model.addAttribute("message", message);
        return "dice";
    }
}

