package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoRepository;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class SessaoRepositoryImplTest {
	
	@Autowired
	private SessaoRepository repository;

	@Autowired
	private DbUnit dbUnit;
	
    @BeforeEach
    public void setUp() {
    	dbUnit.setTableName("sessao");
    	dbUnit.setDatasetPath("src/test/resources/xml/SessaoRepositoryImplTest.xml");
    }
    
    @Test
    public void deveriaPesquisar() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdSessao(-351l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar2() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdSessao(-351l);
    	filtro.setIdTrilha(-110l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar3() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdSessao(-351l);
    	filtro.setIdTrilha(-110l);
    	filtro.setIdManual(-1l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(1, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar4() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdManual(-1l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar5() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdTrilha(-110l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar6() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdTrilha(-110l);
    	filtro.setIdManual(-1l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar7() {
    	SessaoFilter filtro = new SessaoFilter();
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(5, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar8() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdSessao(-25l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar9() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdTrilha(-25l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
    
    @Test
    public void deveriaPesquisar10() {
    	SessaoFilter filtro = new SessaoFilter();
    	filtro.setIdManual(-155l);
    	List<Sessao> pesquisa = repository.pesquisa(filtro);
    	assertEquals(0, pesquisa.size());
    }
}
