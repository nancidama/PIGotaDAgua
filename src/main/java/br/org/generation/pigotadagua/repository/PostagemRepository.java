package br.org.generation.pigotadagua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.pigotadagua.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
}


