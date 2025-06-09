package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DataUtil;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DiferencasUtil;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class OansistaServiceImpl implements OansistaService {
	
	@Autowired
	private OansistaRepository repository;
	@Autowired
	private DataUtil dataUtil;
	@Autowired
	private DiferencasUtil diferencasUtil;
	
	@Override
	public Oansista carrega(Long id) {
		if (id == null || id <= 0)
			return null;
		return repository.carrega(id);
	}

	@Override
	public List<Oansista> pesquisa(OansistaFilter filtro) {
		return repository.pesquisa(filtro);
	}

	@Override
	public List<Oansista> listaTudo() {
		return repository.listaTudo();
	}

	@Override
	public void salva(Oansista oansista) {
		List<String> erros = valida(oansista);
		if (oansista.getId() != null)
			erros.add("Não deve imformar o ID, o mesmo será gerado automaticamente.");
		if (!erros.isEmpty())
			throw new OanseValildationException(erros);
		repository.salva(oansista);
	}

	@Override
	public void atualiza(Oansista oansista) {
		List<String> erros = valida(oansista);
		Oansista oansistaBase = null;
		if (oansista.getId() == null)
			erros.add("O ID do Oansista é obrigatório.");		
		else {
			if ((oansistaBase = carrega(oansista.getId())) == null)
				erros.add("Não é possível atualizar, cadastro não encontrado.");
		}
	    if (!erros.isEmpty())
			throw new OanseValildationException(erros);
	    if (temDiferencas(oansistaBase, oansista)) {
	    	oansistaBase.atualizaCom(oansista);
	    	repository.atualiza(oansistaBase);
	    }
	}

	@Override
	public void remove(Long id) {
		Oansista oansistaBase = carrega(id);
		if (oansistaBase == null)
			throw new OanseValildationException("Não é possível remover, cadastro não encontrado.");
		repository.deleta(id);
	}
	
	private List<String> valida(Oansista oansista) {
		if (oansista == null)
			throw new OanseValildationException("Dados inválidos!");
		List<String> erros = new ArrayList<>();
		if (StringUtils.isBlank(oansista.getNome()))
			erros.add("O nome do Oansista é obrigatório.");
		else if (oansista.getNome().length() > 255)
			erros.add("O nome do Oansista não pode ser tão grande.");
		else if (oansista.getNome().length() < 3)
			erros.add("O nome do Oansista deve ter pelo menos 3 caracteres.");
		if (oansista.getDataNascimento() == null)
			erros.add("A data de nascimento do Oansista é obrigatória.");
		else {
			Integer idade = dataUtil.calcularIdade(oansista.getDataNascimento());
			if (idade < 6 || idade > 14)
				erros.add("Oansista deve ter entre 6 e 14 anos de idade.");
		}
		return erros;
	}
	
	private boolean temDiferencas(Oansista oansista1, Oansista oansista2) {
		if (
			diferencasUtil.temDiferenca(oansista1.getNome(), oansista2.getNome()) ||
			!dataUtil.isMesmaData(oansista1.getDataNascimento(), oansista2.getDataNascimento()) ||
			diferencasUtil.temDiferenca(oansista1.getRua(), oansista2.getRua()) ||
			diferencasUtil.temDiferenca(oansista1.getNumero(), oansista2.getNumero()) ||
			diferencasUtil.temDiferenca(oansista1.getBairro(), oansista2.getBairro())
		) return true;
		
		if (oansista1.getResponsavel() == null && oansista2.getResponsavel() != null)
			return true;
		if (oansista1.getResponsavel() != null && oansista2.getResponsavel() == null)
			return true;
		if (oansista1.getResponsavel() != null && oansista2.getResponsavel() != null) {
			if (diferencasUtil.temDiferenca(oansista1.getResponsavel().getId(), oansista2.getResponsavel().getId()))
				return true;
		}
		return false;
	}

}
