package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.factory.OansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.ResponsavelFactory;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class OansistaRepositoryImplTest {
	
	@Autowired
	private OansistaRepository repository;

	@Autowired
	private DbUnit dbUnit;
	
    @BeforeEach
    public void setUp() {
    	dbUnit.setTableName("oansista");
    	dbUnit.setDatasetPath("src/test/resources/xml/OansistaRepositoryImplTest.xml");
    }
	
	@Test
	public void deveriaCarregar() {
		Oansista oansista = repository.carrega(-2l);		
		assertEquals(oansista.getNome(), "Oansista 2");
		assertNotNull(oansista.getResponsavel());
		Responsavel responsavel = oansista.getResponsavel();
		assertEquals(responsavel.getNome(), "Responsável 2");
	}	

	@Test
	public void deveriaCarregaTudo() {
		List<Oansista> oansistas = repository.listaTudo();
		assertEquals(oansistas.size(), 3);
	}
	
	@Test
	public void deveriaSalvar() {
		Oansista oansista = new OansistaFactory().comNome("nome 2").comDataNascimento(LocalDate.of(2020, 1, 17)).build();
		repository.salva(oansista);		
		OansistaFilter filtro = new OansistaFilter();
		filtro.setNome("nome 2");
		List<Oansista> pesquisa = repository.pesquisa(filtro);
		assertNotNull(pesquisa);
		assertEquals(pesquisa.size(), 1);
	}
	
	@Test
	public void deveriaSalvar2() {
		Oansista oansista = new OansistaFactory().comNome("nome 2").comDataNascimento(LocalDate.of(2020, 1, 17)).build();
		Responsavel responsavel = new ResponsavelFactory().comNome("responsavel").build();
		oansista.setResponsavel(responsavel);
		repository.salva(oansista);
		OansistaFilter filtro = new OansistaFilter();
		filtro.setNome("nome 2");
		List<Oansista> pesquisa = repository.pesquisa(filtro);
		assertNotNull(pesquisa);
		assertEquals(pesquisa.size(), 1);
		Oansista oansistaSalvo = pesquisa.get(0);
		assertNotNull(oansistaSalvo.getResponsavel());
	}
	
	@Test
	public void deveriaAtualizar() {
		Oansista oansista = repository.carrega(-1l);
		oansista.setNome("nome atualizado");
		repository.atualiza(oansista);
		Oansista oansistaSalvo = repository.carrega(-1l);
		assertEquals(oansistaSalvo.getNome(), "nome atualizado");
	}

	@Test
	public void deveriaRemover() {
		repository.deleta(-1l);
		assertNull(repository.carrega(-1l));
	}
}
