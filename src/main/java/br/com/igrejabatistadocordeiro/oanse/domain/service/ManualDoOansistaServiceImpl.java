package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.ManualDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.ManualDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.TrilhaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Sessao;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Trilha;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.TrilhaRepository;

@Service
public class ManualDoOansistaServiceImpl implements ManualDoOansistaService {

	@Autowired
	private ManualDoOansistaRepository manualDoOansistaRepository;	
	@Autowired
	private OansistaRepository oansistaRepository;	
	@Autowired
	private ManualRepository manualRepository;
	@Autowired
	private TrilhaRepository trilhaRepository;
	@Autowired
	private SessaoRepository sessaoRepository;
	@Autowired
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;
	
	@Override
	public List<ManualDoOansista> listaTudo() {
		return manualDoOansistaRepository.listaTudo();
	}

	@Override
	public List<ManualDoOansista> pesquisa(ManualDoOansistaFilter filtro) {		
		return manualDoOansistaRepository.pesquisa(filtro);
	}

	@Override
	public void salvar(ManualDoOansistaDTO dto) {
		List<String> erros = new ArrayList<String>();
		Oansista oansista = oansistaRepository.carrega(dto.getIdOansista());
		Manual manual = manualRepository.carrega(dto.getIdManual());		
		if (oansista == null)
			erros.add("Oansista não encontrado");		
		if (manual == null)
			erros.add("Manual não encontrado");		
		if (!erros.isEmpty())
			throw new OanseValidationException(erros);		
		ManualDoOansista manualDoOansistaBase = manualDoOansistaRepository.carrega(dto.getIdOansista(), dto.getIdManual());
		if (manualDoOansistaBase != null)
			throw new OanseValidationException("Já existe um manual associado a este oansista");		
		ManualDoOansista manualDoOansista = new ManualDoOansista();
		manualDoOansista.setOansista(oansista);
		manualDoOansista.setManual(manual);
		manualDoOansista.setDataInicio(Date.valueOf(LocalDate.now()));
		manualDoOansista.setConcluido(false);
		manualDoOansistaRepository.salva(manualDoOansista);
		
		TrilhaFilter filtroTrilha = new TrilhaFilter();
		filtroTrilha.setIdManual(dto.getIdManual());
		List<Trilha> trilhas = trilhaRepository.pesquisa(filtroTrilha);
		if (trilhas == null || trilhas.isEmpty())
			throw new OanseValidationException("Trilhas não encontradas na base de dados");
		
		SessaoFilter filtroSessao;
	    for (Trilha trilha : trilhas) {
	    	filtroSessao = new SessaoFilter();
	    	filtroSessao.setIdTrilha(trilha.getId());
	    	List<Sessao> sessoes = sessaoRepository.pesquisa(filtroSessao);
	    	if (sessoes == null || sessoes.isEmpty())
				throw new OanseValidationException("Sessoes não encontradas na base de dados");
	    	
	    	for (Sessao sessao : sessoes) {
	    		SessaoDoOansista sessaoDoOansista = new SessaoDoOansista();
	    		sessaoDoOansista.setManualDoOansista(manualDoOansista);
	    		sessaoDoOansista.setOansista(oansista);
	    		sessaoDoOansista.setSessao(sessao);
	    		sessaoDoOansista.setDataInicio(Date.valueOf(LocalDate.now()));
	    		sessaoDoOansista.setConcluido(false);
	    		sessaoDoOansistaRepository.salva(sessaoDoOansista);
	    	}
	    }
	}

	@Override
	public void atualizar(ManualDoOansistaDTO dto) {
		List<String> erros = new ArrayList<String>();
		Oansista oansista = oansistaRepository.carrega(dto.getIdOansista());
		Manual manual = manualRepository.carrega(dto.getIdManual());		
		if (oansista == null)
			erros.add("Oansista não encontrado");		
		if (manual == null)
			erros.add("Manual não encontrado");		
		if (!erros.isEmpty())
			throw new OanseValidationException(erros);		
		ManualDoOansista manualDoOansistaBase = manualDoOansistaRepository.carrega(dto.getIdOansista(), dto.getIdManual());
		if (manualDoOansistaBase == null)
			throw new OanseValidationException("Não tem manual associado a este oansista");		
		manualDoOansistaBase.setDataConclusao(Date.valueOf(LocalDate.now()));
		manualDoOansistaBase.setConcluido(true);
		manualDoOansistaRepository.atualiza(manualDoOansistaBase);
	}

}
