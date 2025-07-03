package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001.dto.SessaoDoOansistaDTO;
import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.SessaoDoOansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.SessaoDoOansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.SessaoDoOansistaRepository;

@Service
public class SessaoDoOansistaServiceImpl implements SessaoDoOansistaService {

	@Autowired
	private SessaoDoOansistaRepository sessaoDoOansistaRepository;	
	
	@Override
	public List<SessaoDoOansista> listaTudo() {
		return sessaoDoOansistaRepository.listaTudo();
	}

	@Override
	public List<SessaoDoOansista> pesquisa(SessaoDoOansistaFilter filtro) {
		return sessaoDoOansistaRepository.pesquisa(filtro);
	}

	@Override
	public void concluirSessao(SessaoDoOansistaDTO dto) {	
		List<String> erros = new ArrayList<String>();
		if (dto.getIdOansista() == null || dto.getIdOansista() <= 0)
			erros.add("o campo idOansista está inválido");
		if (dto.getIdManual() == null || dto.getIdManual() <= 0)
			erros.add("O campo idManual está inválido");
		if (dto.getIdTrilha() == null || dto.getIdTrilha() <= 0)
			erros.add("O campo idTrilha está inválido");
		if (dto.getNumeroDaSessao() == null || dto.getNumeroDaSessao() <= 0)
			erros.add("O campo numeroDaSessao está inválido");
		if (!erros.isEmpty())
			throw new OanseValidationException(erros);

		SessaoDoOansista sessao = sessaoDoOansistaRepository.carrega(dto.getIdOansista(), dto.getIdManual(), dto.getIdTrilha(), dto.getNumeroDaSessao());
	    if (sessao == null)
	        throw new OanseValidationException("Número da sessão não encontrado!");
	    if (sessao.getConcluido())
	        throw new OanseValidationException("Sessão já concluída!");

	    sessao.setDataConclusao(new Date());
	    sessao.setConcluido(true);
	    sessaoDoOansistaRepository.atualiza(sessao);
	}

}