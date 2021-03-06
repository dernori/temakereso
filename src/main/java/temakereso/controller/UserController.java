package temakereso.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import temakereso.service.LoggedInUserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final LoggedInUserService loggedInUserService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(value = {"/me"})
    public String me() {
        return "me";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(value = {"/me/edit"})
    public String editMe() {
        return "form-me";
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(value = {"/mail"})
    public String mail() {
        return "mail";
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @GetMapping(value = {"/topics/add"})
    public String addTopic() {
        return "form-topic";
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @GetMapping(value = {"/topics"})
    public String supervisorTopics(Model model) {
        model.addAttribute("supervisor", loggedInUserService.getLoggedInSupervisor().getId());
        return "supervisor-topics";
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @GetMapping(value = {"/topics/{id}/edit"})
    public String editTopic(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "form-topic";
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(value = {"/applications"})
    public String applications() {
        return "student-applications";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/settings"})
    public String settings() {
        return "admin-settings";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = {"/reports"})
    public String reports() {
        return "admin-reports";
    }

}
