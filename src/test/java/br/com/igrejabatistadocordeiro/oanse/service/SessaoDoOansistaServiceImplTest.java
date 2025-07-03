package br.com.igrejabatistadocordeiro.oanse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessaoDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Clubes;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.service.SessaoDoOansistaService;
import br.com.igrejabatistadocordeiro.oanse.domain.service.SessaoDoOansistaServiceImpl;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DataUtil;
import br.com.igrejabatistadocordeiro.oanse.factory.ManualDoOansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.ManualFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.OansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.SessaoDoOansistaFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.SessaoFactory;
import br.com.igrejabatistadocordeiro.oanse.factory.TrilhaFactory;

public class SessaoDoOansistaServiceImplTest {

	@Mock
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;
	@Mock
	private DataUtil dataUtil;
	@InjectMocks
	private SessaoDoOansistaServiceImpl sessaoDoOansistaServiceImpl;
	SessaoDoOansistaService sessaoDoOansistaService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		sessaoDoOansistaService = sessaoDoOansistaServiceImpl;
	}
	
	@Test
	public void deveriaConcluirUmaSessao() {
		Oansista oansista = new OansistaFactory()
										.comId(10)
										.comNome("Bryan").build();
		Manual manual = new ManualFactory()
									.comId(15l)
									.comClube(Clubes.FAISCA)
									.comDescricao("Manual 1").build();
		Trilha trilha = new TrilhaFactory()
									.comId(20l)
									.comNome("trilha 50")
									.comManual(manual).build();
		Sessao sessao = new SessaoFactory()
									.comId(30l)
									.comNumero(3)
									.comTrilha(trilha).build();
		ManualDoOansista manualDoOansista = new ManualDoOansistaFactory()
														.comId(55l)
														.comOansista(oansista)
														.comManual(manual)
														.comDataInicio(Date.valueOf(LocalDate.of(2024, 2, 10))).build();
		SessaoDoOansista sessaoDoOansista = new SessaoDoOansistaFactory()
														.comOansista(oansista)
														.comManualDoOansista(manualDoOansista)														
														.comSessao(sessao)
														.comDataInicio(Date.valueOf(LocalDate.of(2024, 2, 10))).build();

		when(sessaoDoOansistaRepository.carrega(oansista.getId(),manual.getId(),trilha.getId(),sessao.getNumero())).thenReturn(sessaoDoOansista);
		ArgumentCaptor<SessaoDoOansista> captor = ArgumentCaptor.forClass(SessaoDoOansista.class);
		
		SessaoDoOansistaDTO sessaoDoOansistaDTO = new SessaoDoOansistaDTO(sessaoDoOansista);

		sessaoDoOansistaService.concluirSessao(sessaoDoOansistaDTO);
		
		verify(sessaoDoOansistaRepository, times(1)).atualiza(captor.capture());
		SessaoDoOansista sessaoConcluida = captor.getValue();
		assertEquals(sessaoDoOansista.getId(), sessaoConcluida.getId());
		assertEquals(oansista.getId(), sessaoConcluida.getOansista().getId());
		assertEquals(manualDoOansista.getId(), sessaoConcluida.getManualDoOansista().getId());
		assertEquals(sessao.getId(), sessaoConcluida.getSessao().getId());
		assertEquals(true, sessaoConcluida.getConcluido());		
	}
}
