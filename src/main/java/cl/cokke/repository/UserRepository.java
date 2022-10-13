package cl.cokke.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.cokke.security.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	boolean existsByUsername(String username);
	public User findByUsername(String username);
}
