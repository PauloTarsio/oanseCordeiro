package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class SessaoDoOansistaRepositoryImplTest {
	
	@Autowired
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;

	@Autowired
	private DbUnit dbUnit;
	
	@BeforeEach
	public void setUp() {		
		dbUnit.setDatasetPath("src/test/resources/xml/SessaoDoOansistaRepositoryImplTest.xml");
	}
	
	@Test
	public void deveriaCarregarUmaSessaoDoOansista() {
		SessaoDoOansista sessaoDoOansista = sessaoDoOansistaRepository.carrega(-1l, -1l, -1l, 1);
		assertNotNull(sessaoDoOansista);
	}
	
	@Test
	public void deveriaFiltrarPorOansista() {
	    SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
	    filtro.setIdOansista(-1L);

	    List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);
	    assertEquals(3, resultados.size());
	    assertEquals(2, resultados.stream().filter(e->Boolean.TRUE.equals(e.getConcluido())).count());
	    
	}

	@Test
	public void deveriaFiltrarSomenteConcluidas() {
	    SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
	    filtro.setConcluido(true);

	    List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);
	    assertEquals(2, resultados.size());
	}

	@Test
	public void deveriaFiltrarSomentePendentes() {
	    SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
	    filtro.setConcluido(false);

	    List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);
	    assertEquals(1, resultados.size());
	}

	@Test
	public void deveriaFiltrarPorManualDoOansista() {
	    SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
	    filtro.setIdManual(-1L);

	    List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);
	    assertEquals(3, resultados.size());
	}
	
	@Test
	public void deveriaFiltrarPeloOansistaENumeroDaSessao() {
		SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
		filtro.setIdOansista(-1L);
		filtro.setNumeroDaSessao(1);
		
		List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);
		assertEquals(2, resultados.size());
	}
	
	@Test
	public void deveriaConterNomeDoManual() {
	    SessaoDoOansistaFilter filtro = new SessaoDoOansistaFilter();
	    filtro.setIdOansista(-1L);

	    List<SessaoDoOansista> resultados = sessaoDoOansistaRepository.pesquisa(filtro);

	    for (SessaoDoOansista s : resultados) {
	        String nomeManual = s.getManualDoOansista().getManual().getDescricao();
	        assertEquals("Manual Aventureiros", nomeManual);
	    }
	}
	
	@Test
	public void deveriaConcluirUmaSessao() {
		SessaoDoOansista sessaoDoOansista = sessaoDoOansistaRepository.carrega(-2l);
		assertNull(sessaoDoOansista.getConcluido());		
		sessaoDoOansista.setDataConclusao(Date.valueOf("2025-06-20"));
		sessaoDoOansista.setConcluido(true);
		
		sessaoDoOansistaRepository.atualiza(sessaoDoOansista);
		
		SessaoDoOansista sessaoDoOansistaAtualizado = sessaoDoOansistaRepository.carrega(-2l);
		assertTrue(sessaoDoOansistaAtualizado.getConcluido());
	}

}
