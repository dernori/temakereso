package temakereso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping(value = {"/"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/forms/topics"})
    public String topicForm() {
        return "topic-form-filler";
    }

    @GetMapping(value = {"/forms/topics/{id}"})
    public String topicFormFiller(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "topic-form-filler";
    }

    @GetMapping(value = {"/forms/consultation"})
    public String consultationForm() {
        return "consultation-form-filler";
    }

    @GetMapping(value = {"/forms/consultation/{id}"})
    public String consultationFormFiller(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "consultation-form-filler";
    }

    @GetMapping(value = {"/topics/{id}"})
    public String viewTopic(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "topic";
    }

    @GetMapping(value = {"/reset"})
    public String passwordForgot() {
        return "password-forgot";
    }

    @GetMapping(value = {"/reset/{token}"})
    public String resetAccount(@PathVariable("token") String token, Model model) {
        model.addAttribute("token", token);
        return "reset-account";
    }

}
