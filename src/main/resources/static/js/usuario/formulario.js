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

	if (novo) {
		$.preencherComboPerfil();
		$.preencherIgrejaDoUsuario();		
	}

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

$.preencherIgrejaDoUsuario = function() {
	if (perfil === 'SECRETARIO') {
        $.carregaIgreja(igrejaIdUsuarioLogado);
        $("#igrejaId").prop("disabled", true);
    }
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
				$("#igrejaId").val(data.id);
				$("#igrejaId").removeClass("is-invalid");
				$("#igrejaId").prop("disabled", perfil == 'SECRETARIO');
				$("#igrejaDescricao").val(data.dadosPessoais.descricao);
				$("#igrejaDescricao").removeClass("is-invalid");
			} else {
				$("#igrejaId").val("").addClass("is-invalid");
				$("#igrejaDescricao").val("").addClass("is-invalid");
			}
		},
		error: function(xhr) {
			$("#igrejaId").val("").addClass("is-invalid");
			$("#igrejaId").prop("disabled", false);
			$("#igrejaDescricao").val("").addClass("is-invalid");

			let mensagemErro = "Erro ao carregar igreja.";
			if (xhr.responseJSON && xhr.responseJSON.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
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

$.preencherComboPerfil = function() {
	var $select = $("#perfil");
	$select.empty();
	if (perfil === "ADMIN") {
		$select.append('<option value="ADMIN">Administrador</option>');
		$select.append('<option value="SECRETARIO">Secretario</option>');
		$select.append('<option value="LIDER">Lider</option>');
	} else if (perfil === "SECRETARIO") {
		$select.append('<option value="SECRETARIO">Secretario</option>');
		$select.append('<option value="LIDER">Lider</option>');
	} else {
		$select.append('<option value="">Selecione</option>');
	}
}

$.limpaFormulario = function() {
	$("#formUsuario")[0].reset();
};

$.preencherFormulario = function(json) {
	if (!json) return;
	novo = false;
	$("#login").val(json.login);
	var $select = $("#perfil");
	$select.empty();
	$select.append('<option value="' + json.perfil + '">Secretario</option>');
	$("#id").val(json.id);
	$("#igrejaId").val(json.igrejaId);
	$("#igrejaId").prop('disabled', true);
	$("#igrejaDescricao").val(json.igrejaDescricao);
}

$.validarFormulario = function() {
	let valido = true;
	const camposObrigatorios = [
		"#login",
		"#senha",
		"#perfil"
	];
	if (perfil !== 'ADMIN' && novo) {
		camposObrigatorios.push(
			"#igrejaId",
			"#igrejaDescricao"
		)
	}
	camposObrigatorios.forEach(function(campo) {
		const valor = $(campo).val().trim();
		if (!valor) {
			$(campo).addClass("is-invalid");
			valido = false;
		} else {
			$(campo).removeClass("is-invalid");
		}
	});
	if (perfis.indexOf($("#perfil").val()) === -1) {
		$("#perfil").addClass("is-invalid");
		valido = false;
	}
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