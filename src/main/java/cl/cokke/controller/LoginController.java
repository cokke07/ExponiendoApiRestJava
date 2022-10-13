package cl.cokke.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.security.User;
import cl.cokke.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public String login(@RequestParam String username, @RequestParam String password) {
		return userService.signIn(username, password);
	}
	
	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public String signup(@RequestBody User User) {
		return userService.signUp(User);
	}
}
