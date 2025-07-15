package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
    
	Optional<Clube> findByNome(Clubes nome);
	
}
