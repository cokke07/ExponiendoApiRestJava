package cl.cokke.services;

import java.util.List;
import java.util.Optional;

import cl.cokke.models.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	public Optional<Usuario> findById(Long id);
	//public Optional<Usuario> findAllByGender( String gender);
	public void delete(Long id);
	public Usuario save(Usuario usuario);
	public void update(Usuario usuario);
}
