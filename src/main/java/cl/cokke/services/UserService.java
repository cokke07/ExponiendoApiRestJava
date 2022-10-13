package cl.cokke.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import cl.cokke.security.User;

public interface UserService {

	public void update(User user);
	public List<User> findAll();
	public User findById(Long id);
	public void delete(User user);
	public String signIn(String username, String password);
	public String signUp(User User);
	public UserDetails loadUserByUsername(String username);
}
