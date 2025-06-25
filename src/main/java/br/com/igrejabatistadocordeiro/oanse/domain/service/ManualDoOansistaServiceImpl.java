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
import br.com.igrejabatistadocordeiro.oanse.domain.model.Manual;
import br.com.igrejabatistadocordeiro.oanse.domain.model.ManualDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualDoOansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ManualRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;

@Service
public class ManualDoOansistaServiceImpl implements ManualDoOansistaService {

	@Autowired
	private ManualDoOansistaRepository repository;	
	@Autowired
	private OansistaRepository oansistaRepository;	
	@Autowired
	private ManualRepository manualRepository;
	
	@Override
	public List<ManualDoOansista> listaTudo() {
		return repository.listaTudo();
	}

	@Override
	public List<ManualDoOansista> pesquisa(ManualDoOansistaFilter filtro) {		
		return repository.pesquisa(filtro);
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
		ManualDoOansista manualDoOansistaBase = repository.carrega(dto.getIdOansista(), dto.getIdManual());
		if (manualDoOansistaBase != null)
			throw new OanseValidationException("Já existe um manual associado a este oansista");		
		ManualDoOansista manualDoOansista = new ManualDoOansista();
		manualDoOansista.setOansista(oansista);
		manualDoOansista.setManual(manual);
		manualDoOansista.setDataInicio(Date.valueOf(LocalDate.now()));
		manualDoOansista.setConcluido(false);
		repository.salva(manualDoOansista);
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
		ManualDoOansista manualDoOansistaBase = repository.carrega(dto.getIdOansista(), dto.getIdManual());
		if (manualDoOansistaBase == null)
			throw new OanseValidationException("Não tem manual associado a este oansista");		
		manualDoOansistaBase.setDataConclusao(Date.valueOf(LocalDate.now()));
		manualDoOansistaBase.setConcluido(true);
		repository.atualiza(manualDoOansistaBase);
	}

}
