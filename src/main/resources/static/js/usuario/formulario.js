var novo = true;
$(document).ready(function() {

	const usuarioJson = sessionStorage.getItem("usuarioEdicao");
	if (usuarioJson) {
		const json = JSON.parse(usuarioJson);
		$.preencherFormulario(json);

		// Limpa para não reutilizar depois
		sessionStorage.removeItem("usuarioEdicao");
	}
	
	$.ajustarLegenda();

	$("#btnSalvar").on("click", function(event) {
		event.preventDefault();
		if (!$.validarFormulario()) {
			OanseLib.exibirErro("Por favor, corrija os erros no formulário antes de enviar.");
			return;
		}
		const json = $.montaJson();
		enviarCadastro(json);
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});

	if (novo)
		$.limpaFormulario();
	
	$("#igrejaId").on("change", function() {
		$.carregaIgreja($(this).val());
	});

});

// Envia os dados com AJAX
function enviarCadastro(json) {

	let url = novo ? "/api/v001/usuario" : "/api/v001/usuario/" + json.id;
	let type = novo ? "POST" : "PUT";

	$.ajax({
		url: url,
		type: type,
		contentType: "application/json",
		data: JSON.stringify(json),
		success: function() {
			OanseLib.exibirSucesso("Processo concluído com sucesso!");
			if (novo) {
				$("#formUsuario")[0].reset();
				window.history.back();
			}
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao salvar aluno.";
			if (xhr.responseJSON && xhr.responseJSON.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}

$.carregaIgreja = function(id) {
	if (!id) {
		$("#igrejaDescricao").val("");
		return;
	}
	$.ajax({
		url: "/api/v001/igreja/" + id,
		type: "GET",
		success: function(data) {
			if (data && data.dadosPessoais && data.dadosPessoais.descricao) {
				$("#igrejaDescricao").val(data.dadosPessoais.descricao);
				$("#igrejaDescricao").removeClass("is-invalid");
			} else {
				$("#igrejaDescricao").val("").addClass("is-invalid");
			}
		},
		error: function() {
			$("#igrejaDescricao").val("").addClass("is-invalid");
		}
	});
}

$.ajustarLegenda = function() {
	if (novo) {
		$('#legendaFormulario').text('Cadastro de Usuário - NOVO');
	} else {
		$('#legendaFormulario').text('Cadastro de Usuário - EDICAO');
	}
}

$.limpaFormulario = function() {
	$("#formUsuario")[0].reset();
};

$.preencherFormulario = function(json) {
	if (!json) return;
	novo = false;
	$("#login").val(json.login);
	$("#perfil").val(json.perfil.replace("ROLE_", ""));
	$("#id").val(json.id);
	$("#igrejaId").val(json.igrejaId);
	$("#igrejaDescricao").val(json.igrejaDescricao);
}

$.validarFormulario = function() {
	let valido = true;
	
	const camposObrigatorios = [
		"#login",
		"#senha",
		"#perfil",
		"#igrejaId",
		"#igrejaDescricao",
	];
	camposObrigatorios.forEach(function(campo) {
		const valor = $(campo).val().trim();
		if (!valor) {
			$(campo).addClass("is-invalid");
			valido = false;
		} else {
			$(campo).removeClass("is-invalid");
		}
	});

	return valido;
}

$.montaJson = function() {	
	return {
		id: $("#id").val() || null,
		login: $("#login").val().trim() || null,
		senha: $("#senha").val().trim() || null,
		perfil: $("#perfil").val() || null,
		igrejaId: parseInt($("#igrejaId").val()) || null
	};
}