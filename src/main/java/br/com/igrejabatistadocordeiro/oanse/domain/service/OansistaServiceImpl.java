package br.com.igrejabatistadocordeiro.oanse.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValidationException;
import br.com.igrejabatistadocordeiro.oanse.domain.filter.OansistaFilter;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Oansista;
import br.com.igrejabatistadocordeiro.oanse.domain.model.Responsavel;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.OansistaRepository;
import br.com.igrejabatistadocordeiro.oanse.domain.repository.ResponsavelRepository;
import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;

@Service
public class OansistaServiceImpl implements OansistaService {
	
	@Autowired
	private OansistaRepository repository;
	@Autowired
	private ResponsavelRepository responsavelRepository;
	
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
		List<String> erros = new ArrayList<>();
	    if (oansista.getId() != null)
	        erros.add("Não deve informar o ID, o mesmo será gerado automaticamente.");
	    if (repository.existe(oansista))
	        erros.add("Já existe um Oansista com mesmo nome e data de nascimento.");
	    if (!erros.isEmpty())
	        throw new OanseValidationException(erros);

	    try {
	    	resolveResponsaveis(oansista);
	    	repository.salva(oansista);			
		} catch (Exception e) {
			throw new OanseValidationException("Erro não tratado: " + e.getMessage());
		}
	}

	@Override
	public void atualiza(Oansista oansista) {
		List<String> erros = new ArrayList<String>();
		if (oansista.getId() == null)
			erros.add("O ID do Oansista é obrigatório.");		
		Oansista oansistaBase = null;
		if ((oansistaBase = carrega(oansista.getId())) == null)
			erros.add("Não é possível atualizar, cadastro não encontrado.");
		if (repository.existe(oansista))
			erros.add("Já existe um Oansista na base com mesmo nome e data de nascimento.");
		if (!erros.isEmpty())
			throw new OanseValidationException(erros);
	    
		if (temDiferencas(oansistaBase, oansista)) {
		    try {
		        resolveResponsaveis(oansista);
		        oansistaBase.atualizaCom(oansista);
		        repository.atualiza(oansistaBase);
		    } catch (Exception e) {
		        throw new OanseValidationException("Erro não tratado: " + e.getMessage());
		    }
		}
	}

	@Override
	public void remove(Long id) {
		Oansista oansistaBase = carrega(id);
		if (oansistaBase == null)
			throw new OanseValidationException("Não é possível remover, cadastro não encontrado.");
		repository.deleta(id);
	}
	
	private boolean temDiferencas(Oansista oansista1, Oansista oansista2) {
		ObjectDiffer differ = ObjectDifferBuilder.startBuilding().inclusion().exclude().propertyName("id").and().build();
		DiffNode diff = differ.compare(oansista1, oansista2);
		return !diff.isUntouched();
	}
	
	private void resolveResponsaveis(Oansista oansista) {
		List<Responsavel> responsaveisAtualizados = new ArrayList<>();
	    for (Responsavel responsavel : oansista.getResponsaveis()) {
	        Responsavel existente = repository.carregaPor(responsavel.getTelefone(),responsavel.getEmail());
	        if (existente != null) {
	            responsaveisAtualizados.add(existente);
	        } else {
	            responsavelRepository.salva(responsavel);
	            responsaveisAtualizados.add(responsavel);
	        }
	    }
	    oansista.setResponsaveis(responsaveisAtualizados);
	}

}
