package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Livro;
import br.com.igrejabatistadocordeiro.oanse.domain.model.clube.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.service.LivroService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Manual", description = "Gerencia os manuais (livros) disponíveis para clubes")
public class ManualController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/api/v001/manuais")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SECRETARIO')")
    public ResponseEntity<List<Livro>> listarPorClube(@RequestParam Clubes clube) {
        List<Livro> manuais = livroService.listarPorClube(clube);
        if (manuais.isEmpty())
			return ResponseEntity.noContent().build();
        return ResponseEntity.ok(manuais);
    }
}
