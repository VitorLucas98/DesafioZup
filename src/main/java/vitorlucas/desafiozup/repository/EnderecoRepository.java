package vitorlucas.desafiozup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vitorlucas.desafiozup.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
