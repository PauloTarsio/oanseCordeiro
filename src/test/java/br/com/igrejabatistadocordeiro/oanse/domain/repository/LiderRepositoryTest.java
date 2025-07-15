package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Lider;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LiderRepositoryTest {

    @Autowired
    private LiderRepository liderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long liderId;
    private Long enderecoIdLider;
    private Long enderecoIdIgreja;

    @Test
    public void deveriaExecutarCrudCompletoDoLider() throws Exception {
        criarLiderCompleto();
        buscarLiderPorId();
        atualizarLider();
        deletarLider();
    }

    private void criarLiderCompleto() throws Exception {
        String json = Files.readString(Paths.get("src/test/resources/json/lider.json"));
        Lider lider = objectMapper.readValue(json, Lider.class);

        Lider salvo = liderRepository.save(lider);

        Assertions.assertNotNull(salvo.getId());
        Assertions.assertNotNull(salvo.getDadosPessoais().getId());
        Assertions.assertNotNull(salvo.getIgreja().getId());

        this.liderId = salvo.getId();
        this.enderecoIdLider = salvo.getDadosPessoais().getEndereco().getId();
        this.enderecoIdIgreja = salvo.getIgreja().getDadosPessoais().getEndereco().getId();

        System.out.println("✔️ Líder criado com ID: " + salvo.getId());
    }

    private void buscarLiderPorId() {
        Optional<Lider> liderOpt = liderRepository.findById(liderId);
        Assertions.assertTrue(liderOpt.isPresent());
        System.out.println("✔️ Líder encontrado: " + liderOpt.get().getDadosPessoais().getDescricao());
    }

    private void atualizarLider() {
        Lider lider = liderRepository.findById(liderId).orElseThrow();

        lider.getDadosPessoais().setDescricao("Maria Líder Atualizada");
        lider.getDadosPessoais().setEmail("maria.nova@lider.com");
        
        lider.getDadosPessoais().getEndereco().setId(enderecoIdLider);
        lider.getIgreja().getDadosPessoais().getEndereco().setId(enderecoIdIgreja);

        Lider atualizado = liderRepository.save(lider);

        Assertions.assertEquals("Maria Líder Atualizada", atualizado.getDadosPessoais().getDescricao());
        Assertions.assertEquals("maria.nova@lider.com", atualizado.getDadosPessoais().getEmail());

        System.out.println("✔️ Líder atualizado com sucesso.");
    }

    private void deletarLider() {
        liderRepository.deleteById(liderId);

        Optional<Lider> excluido = liderRepository.findById(liderId);
        Assertions.assertTrue(excluido.isEmpty());

        System.out.println("✔️ Líder deletado com sucesso.");
    }
    
}
