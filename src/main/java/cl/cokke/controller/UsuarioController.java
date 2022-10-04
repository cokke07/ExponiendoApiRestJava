package cl.cokke.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.models.Usuario;
import cl.cokke.services.UsuarioService;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServicio;

	// Metodo encargado de buscar todos los usuarios y mostrarlos en la respuesta
	@GetMapping("/users/getUsers")
	public ResponseEntity<List<Usuario>> listar() {
		// Creamos un objeto de lista de usuarios e instanciamos con un ArrayList
		List<Usuario> usuarios = new ArrayList<Usuario>();

		usuarios = usuarioServicio.findAll(); // Pasamos del servicio a la lista los usuarios encontrados

		if (usuarios.isEmpty()) {
			// Si la lista esta vacia entregamos una respuesta que no tienen contenido la
			// lista
			return new ResponseEntity<>(usuarios, HttpStatus.NO_CONTENT);
		} else {
			// Si la lista existe envia la lista y una respuesta 200
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
	}

	// metodo encargado de crear nuevos usuarios
	@PostMapping("/users/newUser")
	public ResponseEntity<Usuario> insertar(@RequestBody Usuario usuario) {
		try {
			if (usuario != null) {
				Usuario usuarioCreado = usuarioServicio.save(usuario);
				return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// metodo encargado de buscar usuarios por ID
	@GetMapping("/users/getUsers/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {

		try {

			Optional<Usuario> usuarioEncontrado = usuarioServicio.findById(id);

			if (usuarioEncontrado.isPresent()) {
				return new ResponseEntity<>(usuarioEncontrado.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Metodo encargado de actualizar un usuario buscandolo por ID
	@PutMapping("/users/getUsers/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {

		try {
			Optional<Usuario> usuarioEncontrado = usuarioServicio.findById(id);

			if (usuarioEncontrado.isPresent()) {
				usuarioEncontrado.get().setName(usuario.getName());
				usuarioEncontrado.get().setLastName(usuario.getLastName());
				usuarioEncontrado.get().setAge(usuario.getAge());
				usuarioEncontrado.get().setGender(usuario.getGender());
				usuarioEncontrado.get().setEmail(usuario.getEmail());

				return new ResponseEntity<>(usuarioServicio.save(usuarioEncontrado.get()), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// metodo encargado de eliminar un usuario encontrado por su ID
	@DeleteMapping("/users/getUsers/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {

		try {
			Optional<Usuario> usuarioEncontrado = usuarioServicio.findById(id);

			if (usuarioEncontrado.isPresent()) {
				usuarioServicio.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
