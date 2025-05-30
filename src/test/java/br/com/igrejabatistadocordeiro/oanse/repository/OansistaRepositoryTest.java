package br.com.igrejabatistadocordeiro.oanse.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import br.com.igrejabatistadocordeiro.oanse.factory.OansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.model.Oansista;
import dbUnit.DbUnit;

@SpringBootTest
@Import(DbUnit.class)
public class OansistaRepositoryTest {
	
	@Autowired
	private OansistaRepository repository;

	@Autowired
	private DbUnit dbUnit;
	
    @BeforeEach
    public void setUp() {
    	dbUnit.setTableName("oansista");
    	dbUnit.setDatasetPath("src/test/resources/xml/OansistaRepositoryTest.xml");
    }
	
	@Test
	public void deveriaSalvar() {
		Oansista oansista = new OansistaFactory().comNome("nome 2").comDataNascimento(LocalDate.of(2020, 1, 17)).build();
		Oansista oansistaSalvo = repository.save(oansista);
		assertNotNull(oansistaSalvo);
	}

//	@Test
//	public void testFindById() {
//		fail("Not yet implemented");
//	}

	@Test
	public void deveriaCarregaTudo() {
		List<Oansista> oansistas = repository.findAll();
		assertEquals(oansistas.size(), 3);
	}

}
