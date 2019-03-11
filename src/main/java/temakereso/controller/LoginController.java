package temakereso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Hibás felhasználónév vagy jelszó!");
        }

        if (logout != null) {
            model.addObject("msg", "Sikeresen kijelentkeztél.");
        }
        model.setViewName("login");
        return model;
    }

    @GetMapping(value = {"/registration"})
    public String registration() {
        return "registration";
    }

}
