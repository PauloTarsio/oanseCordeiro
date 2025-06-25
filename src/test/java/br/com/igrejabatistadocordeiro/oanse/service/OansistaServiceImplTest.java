package br.com.igrejabatistadocordeiro.oanse.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.service.OansistaService;
import br.com.igrejabatistadocordeiro.oanse.domain.service.OansistaServiceImpl;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DataUtil;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DiferencasUtil;
import br.com.igrejabatistadocordeiro.oanse.factory.OansistaFactory;

public class OansistaServiceImplTest {

	@Mock
	private OansistaRepository repository;
	@Mock
	private DataUtil dataUtil;
	@Mock
	private DiferencasUtil diferencasUtil;
	@InjectMocks
	private OansistaServiceImpl serviceImpl;	
	private OansistaService service;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		service = serviceImpl;
	}
	
	@Test
	public void deveriaCarregar() {
		when(repository.carrega(1l)).thenReturn(new OansistaFactory().comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build());
		Oansista oansista = service.carrega(1l);
		assertNotNull(oansista);
		assertEquals("Paulo", oansista.getNome());
	}
	
	@Test
	public void deveriaPesquisar() {
		OansistaFilter filtro = new OansistaFilter();
		filtro.setNome("Paulo");
		when(repository.pesquisa(filtro)).thenReturn(Arrays.asList(new OansistaFactory().comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build()));
		List<Oansista> pesquisa = service.pesquisa(filtro);
		assertNotNull(pesquisa);
		assertEquals(1, pesquisa.size());
		assertEquals("Paulo", pesquisa.get(0).getNome());
	}
	
	@Test
	public void deveriaListarTudo() {
		when(repository.listaTudo()).thenReturn(Arrays.asList(new OansistaFactory().comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build()));
		List<Oansista> oansistas = service.listaTudo();
		assertNotNull(oansistas);
		assertEquals(1, oansistas.size());
	}
	
	@Test
	public void deveriaSalvar() {
		Oansista oansista = new OansistaFactory().comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(dataUtil.calcularIdade(oansista.getDataNascimento())).thenReturn(10);
		ArgumentCaptor<Oansista> captor = ArgumentCaptor.forClass(Oansista.class);		
		service.salva(oansista);		
		verify(repository, times(1)).salva(captor.capture());
		Oansista oasistaCapturado = captor.getValue();
		assertEquals(oansista, oasistaCapturado);
	}

	@Test
	public void deveriaAtualizar() {
		Oansista oansistaAtualizado = new OansistaFactory().comId(75).comNome("Paulo Alves").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		Oansista oansistaBase = new OansistaFactory().comId(75).comNome("Paulo Araujo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(dataUtil.calcularIdade(oansistaAtualizado.getDataNascimento())).thenReturn(10);
		when(diferencasUtil.temDiferenca(anyString(), anyString())).thenReturn(true);
		when(repository.carrega(anyLong())).thenReturn(oansistaBase);
		ArgumentCaptor<Oansista> captor = ArgumentCaptor.forClass(Oansista.class);
		service.atualiza(oansistaAtualizado);
		verify(repository, times(1)).atualiza(captor.capture());
		Oansista oasistaCapturado = captor.getValue();
		assertEquals(oasistaCapturado.getNome(), oansistaAtualizado.getNome());
	}
	
	@Test
	public void deveriaRemover() {
		Oansista oansistaBase = new OansistaFactory().comId(50).comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(repository.carrega(78l)).thenReturn(oansistaBase);
		service.remove(78l);
		verify(repository, times(1)).deleta(78l);
	}
	
	@Test
	public void deveriaDarErroAoSalvar1() {
		Oansista oansista = new OansistaFactory().comId(50).comNome("Paulo").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(dataUtil.calcularIdade(oansista.getDataNascimento())).thenReturn(10);
		ArgumentCaptor<Oansista> captor = ArgumentCaptor.forClass(Oansista.class);
		try {
			service.salva(oansista);
		} catch (OanseValidationException e) {
			assertEquals(e.getErros().get(0), "Não deve imformar o ID, o mesmo será gerado automaticamente.");
		}
		verify(repository, times(0)).salva(captor.capture());		
	}
	
	@Test
	public void deveriaDarErroAoAtualizar1() {
		Oansista oansistaAtualizado = new OansistaFactory().comNome("Paulo Alves").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(dataUtil.calcularIdade(oansistaAtualizado.getDataNascimento())).thenReturn(10);
		ArgumentCaptor<Oansista> captor = ArgumentCaptor.forClass(Oansista.class);
		try {
			service.atualiza(oansistaAtualizado);
			fail();
		} catch (OanseValidationException e) {
			assertEquals(e.getErros().get(0), "O ID do Oansista é obrigatório.");
		}
		verify(repository, times(0)).atualiza(captor.capture());		
	}
	
	@Test
	public void deveriaDarErroAoAtualizar2() {
		Oansista oansistaAtualizado = new OansistaFactory().comId(75).comNome("Paulo Alves").comDataNascimento(Date.valueOf(LocalDate.of(1982, 11, 5))).build();
		when(dataUtil.calcularIdade(oansistaAtualizado.getDataNascimento())).thenReturn(10);
		when(repository.carrega(anyLong())).thenReturn(null);
		ArgumentCaptor<Oansista> captor = ArgumentCaptor.forClass(Oansista.class);
		try {
			service.atualiza(oansistaAtualizado);
			fail();
		} catch (OanseValidationException e) {
			assertEquals(e.getErros().get(0), "Não é possível atualizar, cadastro não encontrado.");
		}
		verify(repository, times(0)).atualiza(captor.capture());
	}
	
	@Test
	public void deveriaDarErroAoRemover1() {		
		when(repository.carrega(78l)).thenReturn(null);
		try {
			service.remove(78l);
			fail();
		} catch (OanseValidationException e) {
			assertEquals(e.getErros().get(0), "Não é possível remover, cadastro não encontrado.");
		}
		verify(repository, times(0)).deleta(78l);
	}

}
