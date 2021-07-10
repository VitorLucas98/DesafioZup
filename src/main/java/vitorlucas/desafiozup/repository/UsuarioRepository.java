package vitorlucas.desafiozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vitorlucas.desafiozup.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByCpf(String cpf);
	Usuario findByEmail(String email);
}
