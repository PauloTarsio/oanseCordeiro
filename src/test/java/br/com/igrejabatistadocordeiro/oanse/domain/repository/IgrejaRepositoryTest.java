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

import br.com.igrejabatistadocordeiro.oanse.domain.model.Igreja;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IgrejaRepositoryTest {

    @Autowired
    private IgrejaRepository igrejaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long igrejaId;

    @Test
    public void deveriaExecutarCrudCompletoDaIgreja() throws Exception {
        criarIgreja();
        buscarIgrejaPorId();
        atualizarIgreja();
        deletarIgreja();
    }

    private void criarIgreja() throws Exception {
        String json = Files.readString(Paths.get("src/test/resources/json/igreja.json"));
        Igreja igreja = objectMapper.readValue(json, Igreja.class);

        Igreja salva = igrejaRepository.save(igreja);

        Assertions.assertNotNull(salva.getId());
        Assertions.assertNotNull(salva.getPessoa().getId());

        this.igrejaId = salva.getId();

        System.out.println("✔️ Igreja criada com ID: " + igrejaId);
    }

    private void buscarIgrejaPorId() {
        Optional<Igreja> encontrada = igrejaRepository.findById(igrejaId);
        Assertions.assertTrue(encontrada.isPresent());
        System.out.println("✔️ Igreja encontrada: " + encontrada.get().getPessoa().getDescricao());
    }

    private void atualizarIgreja() {
        Igreja igreja = igrejaRepository.findById(igrejaId).orElseThrow();

        igreja.getPessoa().setDescricao("Pr. João Atualizado");
        igreja.getPessoa().setEmail("joao.atualizado@igreja.com");

        Igreja atualizada = igrejaRepository.save(igreja);

        Assertions.assertEquals("Pr. João Atualizado", atualizada.getPessoa().getDescricao());
        Assertions.assertEquals("joao.atualizado@igreja.com", atualizada.getPessoa().getEmail());

        System.out.println("✔️ Igreja atualizada com sucesso.");
    }

    private void deletarIgreja() {
        igrejaRepository.deleteById(igrejaId);
        Optional<Igreja> deletada = igrejaRepository.findById(igrejaId);
        Assertions.assertTrue(deletada.isEmpty());
        System.out.println("✔️ Igreja deletada com sucesso.");
    }
}
