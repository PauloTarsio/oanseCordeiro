package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.util.DataUtilDate;
import br.com.igrejabatistadocordeiro.oanse.domain.util.StringUtils;

@Service
public class OansistaServiceImpl implements OansistaService {
	
	private OansistaRepository repository;
	
	@Autowired
	public void setOansistaRepository(OansistaRepository oansistaRepository) {
		this.repository = oansistaRepository;
	}

	@Override
	public Oansista carrega(Long id) {
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
		if (oansista.getId() != null &&	carrega(oansista.getId()) != null);
			erros.add("Já existe um cadastrado com o ID informado.");
		if (!erros.isEmpty())
			throw new OanseValildationException(erros);
		repository.salva(oansista);
	}

	@Override
	public void atualiza(Long id, Oansista oansista) {
		List<String> erros = valida(oansista);
		if (oansista.getId() == null ||	carrega(oansista.getId()) == null);
		    erros.add("Não é possível atualizar, cadastro não encontrado.");
	    if (!erros.isEmpty())
			throw new OanseValildationException(erros);
		repository.atualiza(oansista);
	}

	@Override
	public void remove(Long id) {
		if (id == null || id <= 0)
			throw new OanseValildationException("Não é possível remover, cadastro não encontrado.");
		repository.deleta(id);
	}
	
	private List<String> valida(Oansista oansista) {
		if (oansista == null)
			throw new OanseValildationException("Dados inválidos!");
		List<String> erros = new ArrayList<>();
		if (StringUtils.isNotBlank(oansista.getNome()))
			erros.add("O nome do Oansista é obrigatório.");
		else if (oansista.getNome().length() > 255)
			erros.add("O nome do Oansista não pode ser tão grande.");
		else if (oansista.getNome().length() < 3)
			erros.add("O nome do Oansista deve ter pelo menos 3 caracteres.");
		if (oansista.getDataNascimento() == null)
			erros.add("A data de nascimento do Oansista é obrigatória.");
		else {
			int idade = DataUtilDate.calcularIdade(oansista.getDataNascimento());
			if (idade < 6 || idade > 14)
				erros.add("O Oansista deve ter entre 6 e 14 anos de idade.");
		}
		return erros;
	}

}
