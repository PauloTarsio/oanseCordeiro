package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.service.AlunoManualService;

@RestController
@RequestMapping
public class AlunoManualController {   
   
    @Autowired
    private AlunoManualService alunoManualService;

    @GetMapping("/api/aluno-manual")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    public ResponseEntity<?> pesquisa(@RequestParam(required = false) Long alunoId, @RequestParam(required = false) Long livroId) {
        var resultado = alunoManualService.pesquisa(alunoId, livroId);
        return ResponseEntity.ok(resultado);
    }
    
    @PostMapping("/api/aluno-manual")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    public ResponseEntity<?> associa(@RequestParam Long alunoId, @RequestParam Long livroId) {
    	alunoManualService.salva(alunoId, livroId);    	
		return ResponseEntity.created(URI.create("/api/aluno-manual")).build();
    }

    @PutMapping("/api/aluno-manual/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    public ResponseEntity<?> conclui(@PathVariable Long id) {
        alunoManualService.conclui(id);
        return ResponseEntity.ok().build();
    }

}