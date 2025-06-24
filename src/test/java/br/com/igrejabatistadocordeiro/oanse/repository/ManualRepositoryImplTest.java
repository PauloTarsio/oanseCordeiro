package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class ManualRepositoryImplTest {

	@Autowired
	private ManualRepository repository;
	
	@Autowired
	private DbUnit dbUnit;
	
	@BeforeEach
	public void setUp() {
		dbUnit.setTableName("manual");
		dbUnit.setDatasetPath("src/test/resources/xml/ManualRepositoryImplTest.xml");
	}
	
	@Test
	public void deveriaListarTudo() {
		List<Manual> manuais = repository.listaTudo();
		assertEquals(5, manuais.size());
	}
	
	@Test
	public void deveriaPesquisarDoisManuaisParaClubeFaisca() {
		ManualFilter filtro = new ManualFilter();
		filtro.setClube("faisca");
	    List<Manual> resultado = repository.pesquisa(filtro);
	    assertEquals(2, resultado.size());
	}

	@Test
	public void deveriaPesquisarNenhumManualParaClubeFlama() {
		ManualFilter filtro = new ManualFilter();
		filtro.setClube("flama");
	    List<Manual> resultado = repository.pesquisa(filtro);
	    assertEquals(0, resultado.size());
	}

	@Test
	public void deveriaPesquisarDoisManuaisParaClubeTocha() {
		ManualFilter filtro = new ManualFilter();
		filtro.setClube("tocha");
	    List<Manual> resultado = repository.pesquisa(filtro);
	    assertEquals(2, resultado.size());
	}
	
	@Test
	public void deveriaPesquisarTodos() {
		List<Manual> resultado = repository.pesquisa(new ManualFilter());
		assertEquals(5, resultado.size());
	}

}
