package br.org.generation.pigotadagua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.pigotadagua.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	public List<Tema> findAllByTemaContainingIgnoreCase(String tema);

	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);

}
