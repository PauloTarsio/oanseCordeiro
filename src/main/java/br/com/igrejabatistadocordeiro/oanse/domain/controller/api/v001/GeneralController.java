package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class GeneralController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String ERRO_INESPERADO = "Não foi possível concluir a operação devido a uma falha inesperada. Caso a falha persista após repetir o processo, contate o Administrador.";
	
	protected static final String MSG_PROCESSO_SUCESSO = "Processo concluído!";
	protected static final String MSG_SALVO_SUCESSO = "Salvo com sucesso!";
	protected static final String MSG_ATUALIZADO_SUCESSO = "Atualizado com sucesso!";
	protected static final String MSG_EXCLUIDO_SUCESSO = "Excluido com sucesso!";
	
	protected static final String MSG_NAO_ENCONTRADO = "Não encontrado.";
	
	protected Response response;
	
	protected ResponseEntity<?> adicionaMensagemDeErro(String msg) {
		return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, msg));
	}

}
