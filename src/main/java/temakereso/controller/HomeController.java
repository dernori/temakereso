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

    @GetMapping(value = {"/forms"})
    public String generalForm() {
        return "form-filler";
    }

    @GetMapping(value = {"/forms/{id}"})
    public String formFromTopic(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "form-filler";
    }

    @GetMapping(value = {"/topics/{id}"})
    public String viewTopic(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "topic";
    }

}
