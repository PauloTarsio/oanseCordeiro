var novo = true;
$(document).ready(function() {

	const usuarioJson = sessionStorage.getItem("usuarioEdicao");
	if (usuarioJson) {
		const json = JSON.parse(usuarioJson);
		$.preencherFormulario(json);

		// Limpa para não reutilizar depois
		sessionStorage.removeItem("usuarioEdicao");
	}

	$("#btnSalvar").on("click", function(event) {
		event.preventDefault();
		if (!$.validarFormulario()) {
			OanseLib.exibirErro("Por favor, corrija os erros no formulário antes de enviar.");
			return;
		}
		$.novo();
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});

	if (novo)
		$.limpaFormulario();

});

$.limpaFormulario = function() {
	$("#formUsuario")[0].reset();
};

$.preencherFormulario = function(json) {
	if (!json) return;
	novo = false;
	$("#login").val(json.login);
	$("#perfil").val(json.perfil.replace("ROLE_", ""));
	$("#id").val(json.id);
}

$.validarFormulario = function() {
	let valido = true;

	const login = $("#login").val().trim();
	if (login === "") {
		$("#login").addClass("is-invalid");
		valido = false;
	} else {
		$("#login").removeClass("is-invalid");
	}

	const senha = $("#senha").val().trim();
	if (senha === "") {
		$("#senha").addClass("is-invalid");
		valido = false;
	} else {
		$("#senha").removeClass("is-invalid");
	}

	const perfil = "ROLE_" + $("#perfil").val();
	if (!perfis.includes(perfil)) {
		$("#perfil").addClass("is-invalid");
		valido = false;
	} else {
		$("#perfil").removeClass("is-invalid");
	}

	return valido;
}

$.novo = function() {
	var novoUsuario = $.montaJson();
	$.ajax({
		url: "/api/v001/usuario",
		type: "POST",
		contentType: "application/json",
		data: novoUsuario,
		success: function() {
			OanseLib.exibirSucesso("Usuário salvo com sucesso.");
			if (novo) {
				$("#formUsuario")[0].reset();
				window.history.back();
			}
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao salvar usuário.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}

$.montaJson = function() {
	let usuario = {
		login: $("#login").val().trim(),
		senha: $("#senha").val().trim(),
		perfil: $("#perfil").val()
	};
	return JSON.stringify(usuario);
}