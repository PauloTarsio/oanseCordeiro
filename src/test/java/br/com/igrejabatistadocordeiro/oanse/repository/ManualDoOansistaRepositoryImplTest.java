package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class ManualDoOansistaRepositoryImplTest {
	
	@Autowired
	private ManualDoOansistaRepository repository;
	
	@Autowired
	private OansistaRepository oansistaRepository;
	
	@Autowired
	private ManualRepository manualRepository;

	@Autowired
	private DbUnit dbUnit;
	
	@BeforeEach
	public void setUp() {		
		dbUnit.setDatasetPath("src/test/resources/xml/ManualDoOansistaRepositoryImplTest.xml");
	}
	
	@Test
	public void deveriaPesquisar() {
		ManualDoOansistaFilter filtro = new ManualDoOansistaFilter();
		filtro.setIdOansista(-1l);
		filtro.setConcluido(true);
		List<ManualDoOansista> pesquisa = repository.pesquisa(filtro);
		
		ManualDoOansista manualDoOansista = pesquisa.get(0);
		assertEquals(1, pesquisa.size());
		assertTrue(manualDoOansista.getConcluido());
		
		Oansista oansista = manualDoOansista.getOansista();
		assertEquals("Oansista 1", oansista.getNome());		
		
		Manual manual = manualDoOansista.getManual();
		assertEquals(-1l, manual.getId());
		assertEquals("Manual do Saltador", manual.getDescricao());
		assertEquals(Clubes.FAISCA, manual.getClube());
	}
	
	@Test
	public void deveriaPesquisar2() {
		ManualDoOansistaFilter filtro = new ManualDoOansistaFilter();
		filtro.setIdOansista(-2l);
		List<ManualDoOansista> pesquisa = repository.pesquisa(filtro);
		
		ManualDoOansista manualDoOansista = pesquisa.get(0);
		assertEquals(1, pesquisa.size());
		assertFalse(manualDoOansista.getConcluido());
		
		Oansista oansista = manualDoOansista.getOansista();
		assertEquals("Oansista 2", oansista.getNome());		
		
		Manual manual = manualDoOansista.getManual();
		assertEquals(-3l, manual.getId());
		assertEquals("Carneiro", manual.getDescricao());
		assertEquals(Clubes.TOCHA, manual.getClube());
	}
	
	@Test
	public void deveriaPesquisar3() {
		ManualDoOansistaFilter filtro = new ManualDoOansistaFilter();
		filtro.setIdOansista(-2l);
		filtro.setIdManual(-3l);
		List<ManualDoOansista> pesquisa = repository.pesquisa(filtro);
		
		ManualDoOansista manualDoOansista = pesquisa.get(0);
		assertEquals(1, pesquisa.size());
		assertFalse(manualDoOansista.getConcluido());
		
		Oansista oansista = manualDoOansista.getOansista();
		assertEquals("Oansista 2", oansista.getNome());		
		
		Manual manual = manualDoOansista.getManual();
		assertEquals(-3l, manual.getId());
		assertEquals("Carneiro", manual.getDescricao());
		assertEquals(Clubes.TOCHA, manual.getClube());
	}
	
	@Test
	public void deveriaPesquisar4() {
		ManualDoOansistaFilter filtro = new ManualDoOansistaFilter();
		filtro.setClube("FAISCA");
		filtro.setConcluido(false);
		List<ManualDoOansista> pesquisa = repository.pesquisa(filtro);
		assertEquals(1, pesquisa.size());
		
		ManualDoOansista manualDoOansista = pesquisa.get(0);
		assertFalse(manualDoOansista.getConcluido());
		
		Oansista oansista = manualDoOansista.getOansista();
		assertEquals("Oansista 1", oansista.getNome());		
		
		Manual manual = manualDoOansista.getManual();
		assertEquals(-2l, manual.getId());
		assertEquals("Manual do Caminhante", manual.getDescricao());
		assertEquals(Clubes.FAISCA, manual.getClube());
	}
	
	@Test
	public void deveriaSalvar() {
		Oansista oasista3 = oansistaRepository.carrega(-3l);
		Manual manualTocha = manualRepository.carrega(-5l);
		
		ManualDoOansista manualDoOansista = new ManualDoOansista();
		manualDoOansista.setOansista(oasista3);
		manualDoOansista.setManual(manualTocha);
		manualDoOansista.setDataInicio(Date.valueOf("2025-01-01"));
		manualDoOansista.setDataConclusao(Date.valueOf("2025-12-31"));
		manualDoOansista.setConcluido(true);
		
		repository.salva(manualDoOansista);

		List<ManualDoOansista> todos = repository.listaTudo();
		assertEquals(4, todos.size());
	}
	
	@Test
	public void deveriaAtualizar() {
		ManualDoOansista manualDoOansista = repository.carrega(-3l);
		manualDoOansista.setDataConclusao(Date.valueOf("2024-01-15"));
		manualDoOansista.setConcluido(true);
		
		repository.atualiza(manualDoOansista);
		
		ManualDoOansista manualDoOansistaAtualizado = repository.carrega(-3l);
		assertTrue(manualDoOansistaAtualizado.getConcluido());
	}
}
