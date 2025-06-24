package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.TrilhaRepository;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class TrilhaRepositoryImplTest {
	
	@Autowired
	private TrilhaRepository repository;

	@Autowired
	private DbUnit dbUnit;
	
    @BeforeEach
    public void setUp() {
    	dbUnit.setTableName("oansista");
    	dbUnit.setDatasetPath("src/test/resources/xml/TrilhaRepositoryImplTest.xml");
    }
    
    @Test
    public void deveriaPesquisar() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdManual(-1l);
    	filtro.setIdTrilha(-110l);
    	filtro.setNome("Trilha");
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar2() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdManual(-1l);
    	filtro.setIdTrilha(-110l);
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar3() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdManual(-1l);
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar4() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdTrilha(-110l);
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar5() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setNome("Trilha 3");
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar6() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdManual(-10l);
    	filtro.setNome("Trilha 3");
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar7() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setNome("Trilha 30");
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar8() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	filtro.setIdManual(-1l);
    	filtro.setIdTrilha(-190l);
    	filtro.setNome("Trilha 3");
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
    @Test
    public void deveriaPesquisar9() {
    	TrilhaFilter filtro = new TrilhaFilter();
    	
    	List<Trilha> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
}
