package br.com.igrejabatistadocordeiro.oanse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualService;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualServiceImpl;
import br.com.igrejabatistadocordeiro.oanse.factory.ManualFactory;

public class ManualServiceImplTest {

	@Mock
	private ManualRepository repository;
	@InjectMocks
	private ManualServiceImpl serviceImpl;
	private ManualService service;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		service = serviceImpl;
	}
	
	@Test
	public void deveriaListarTudo() {
		when(repository.listaTudo()).thenReturn(umaListaDeManuais());
		List<Manual> manuais = service.listaTudo();
		assertEquals(2, manuais.size());
		verify(repository, times(1)).listaTudo();
	}
	
	@Test
	public void deveriaPesquisar() {
		ManualFilter filtro = new ManualFilter();
		filtro.setClube("faisca");
		when(repository.pesquisa(filtro)).thenReturn(umaListaDeManuais());
		List<Manual> manuais = service.pesquisa(filtro);
		assertEquals(2, manuais.size());
		verify(repository, times(1)).pesquisa(filtro);
	}

	private List<Manual> umaListaDeManuais() {
		List<Manual> manuais = new ArrayList<>();		
		manuais.add(new ManualFactory().comClube(Clubes.FAISCA).comDescricao("Manual do Saltador").build());
		manuais.add(new ManualFactory().comClube(Clubes.TOCHA).comDescricao("Leão").build());
		return manuais;
	}
}
