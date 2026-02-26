package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clube;
import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clubes;

public interface ClubeRepository extends JpaRepository<Clube, Long> {
    
	Optional<Clube> findByNome(Clubes nome);
	
}
