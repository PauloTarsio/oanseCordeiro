package br.com.igrejabatistadocordeiro.oanse.domain.controller.api.v001;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import br.com.igrejabatistadocordeiro.oanse.domain.exceptions.OanseValildationException;

public class GeneralController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String ERRO_INESPERADO = "Não foi possível concluir a operação devido a uma falha inesperada. Caso a falha persista após repetir o processo, contate o Administrador.";
	
	protected static final String MSG_PROCESSO_SUCESSO = "Processo concluído!";
	protected static final String MSG_SALVO_SUCESSO = "Salvo com sucesso!";
	protected static final String MSG_ATUALIZADO_SUCESSO = "Atualizado com sucesso!";
	protected static final String MSG_EXCLUIDO_SUCESSO = "Excluido com sucesso!";

	public ResponseEntity<Response> respostaComSucesso(String mensagem) {
		return ResponseEntity.ok().body(new Response(StatusIntegracao.SUCESSO, mensagem));
	}
	
	public ResponseEntity<Response> respostaComFalha(List<String> erros) {
		return ResponseEntity.badRequest().body(new Response(StatusIntegracao.FALHA, erros));
	}
	
	protected void adicionaMensagemDeErro(Exception e) {
		adicionaMensagemDeErro(e, 500);
	}
	
	protected void adicionaMensagemDeErro(String msg) {
		adicionaMensagemDeErro(new OanseValildationException(msg), 500);
	}
	
	protected void adicionaMensagemDeErro(String msg, int statusCode) {
		adicionaMensagemDeErro(new OanseValildationException(msg), statusCode);
	}
	
	protected void adicionaMensagemDeErro(Exception e, int statusCode) {

	}
	
	protected void adicionaMensagemDeErroAPI(Exception e) {
		adicionaMensagemDeErroAPI(e, !(e instanceof OanseValildationException) ? 500 : 512);
	}
	
	protected void adicionaMensagemDeErroAPI(Exception e, int statusCode) {

	}

}
