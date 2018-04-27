package temakereso.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

	// TODO me page
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
	@GetMapping(value={"/me"})
	public String me() {
		return "me";
	}

	// TODO edit-me page
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
	@GetMapping(value={"/me/edit"})
	public String editMe() {
		return "form-me";
	}

	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	@GetMapping(value={"/topics/add"})
	public String addTopic() {
		return "form-topic";
	}

	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	@GetMapping(value={"/topics/{id}/edit"})
	public String editTopic(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "form-topic";
	}

	// TODO messages
	@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_SUPERVISOR')")
	@GetMapping(value={"/messages"})
	public String messages() {
		return "messages";
	}

	// TODO settings page
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/settings"})
	public String settings() {
		return "settings";
	}

	// TODO reports page
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value={"/reports"})
	public String reports() {
		return "reports";
	}

}
