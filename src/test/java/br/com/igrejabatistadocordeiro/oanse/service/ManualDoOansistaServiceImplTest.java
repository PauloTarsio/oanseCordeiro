package br.com.igrejabatistadocordeiro.oanse.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ManualDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.TrilhaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualDoOansistaService;
import br.com.igrejabatistadocordeiro.oanse.domain.service.ManualDoOansistaServiceImpl;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DataUtil;
import br.com.igrejabatistadocordeiro.oanse.factory.ManualDoOansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.ManualFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.OansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.SessaoFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.TrilhaFactory;

public class ManualDoOansistaServiceImplTest {
	
	@Mock
	private ManualDoOansistaRepository repository;
	@Mock
	private OansistaRepository oansistaRepository;
	@Mock
	private ManualRepository manualRepository;
	@Mock
	private TrilhaRepository trilhaRepository;
	@Mock
	private SessaoRepository sessaoRepository;
	@Mock
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;
	@Mock
	private DataUtil dataUtil;
	@InjectMocks
	private ManualDoOansistaServiceImpl serviceImpl;
	private ManualDoOansistaService service;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		service = serviceImpl;
	}

	@Test
	public void deveriaSalvar() {
		ManualDoOansistaDTO dto = new ManualDoOansistaDTO();
		dto.setIdOansista(1l);
		dto.setIdManual(1l);
    	
		ArgumentCaptor<ManualDoOansista> captor = ArgumentCaptor.forClass(ManualDoOansista.class);
		when(oansistaRepository.carrega(dto.getIdOansista())).thenReturn(new OansistaFactory().comId(1).build());
		when(manualRepository.carrega(dto.getIdManual())).thenReturn(new ManualFactory().comId(1l).build());
		when(trilhaRepository.pesquisa(any(TrilhaFilter.class))).thenReturn(Arrays.asList(new TrilhaFactory().comId(1l).build()));
		when(sessaoRepository.pesquisa(any(SessaoFilter.class))).thenReturn(Arrays.asList(new SessaoFactory().comId(1l).build()));

		service.salvar(dto);

		verify(repository, times(1)).salva(captor.capture());
		ManualDoOansista manualDoOansista = captor.getValue();
		assertNotNull(manualDoOansista.getOansista());
		assertNotNull(manualDoOansista.getManual());
	}
	
	@Test
	public void deveriaAtualizar() {
		ManualDoOansistaDTO dto = new ManualDoOansistaDTO();
		dto.setIdOansista(1l);
		dto.setIdManual(1l);		
		ArgumentCaptor<ManualDoOansista> captor = ArgumentCaptor.forClass(ManualDoOansista.class);
		when(oansistaRepository.carrega(dto.getIdOansista())).thenReturn(new OansistaFactory().comId(1).build());
		when(manualRepository.carrega(dto.getIdManual())).thenReturn(new ManualFactory().comId(1l).build());
		when(repository.carrega(dto.getIdOansista(), dto.getIdManual())).thenReturn(new ManualDoOansistaFactory().comId(2l).build());
		
		service.atualizar(dto);
		
		verify(repository, times(1)).atualiza(captor.capture());
		ManualDoOansista manualDoOansista = captor.getValue();
		assertNotNull(manualDoOansista);
	}
}
