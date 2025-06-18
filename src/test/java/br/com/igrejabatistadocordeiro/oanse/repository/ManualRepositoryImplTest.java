package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.model.Clube;
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
	public void deveriaPesquisarPeloClube() {
		List<Manual> pesquisa = repository.pesquisa(Clube.FAISCA);
		assertEquals(2, pesquisa.size());
		
		List<Manual> pesquisa2 = repository.pesquisa(Clube.FLAMA);
		assertEquals(0, pesquisa2.size());
		
		List<Manual> pesquisa3 = repository.pesquisa(Clube.TOCHA);
		assertEquals(2, pesquisa3.size());
	}
}
