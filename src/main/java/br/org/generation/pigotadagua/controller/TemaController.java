package br.org.generation.pigotadagua.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.org.generation.pigotadagua.model.Tema;
import br.org.generation.pigotadagua.repository.TemaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/temas")
public class TemaController {

	@Autowired
	public TemaRepository temaRepository;

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/tema/{tema}")
	public ResponseEntity<List<Tema>> getByTema(@PathVariable String tema) {

		return ResponseEntity.ok(temaRepository.findAllByTemaContainingIgnoreCase(tema));

	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {

		return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));

	}

	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody @Valid Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
	}

	@PutMapping
	public ResponseEntity<Tema> putTema(@Valid @RequestBody Tema tema){
		return temaRepository.findById(tema.getId())
				.map(resposta -> ResponseEntity.ok(temaRepository.save(tema)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deletaTema(@PathVariable Long id){
        return temaRepository.findById(id)
                .map(resposta -> {
                    temaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
