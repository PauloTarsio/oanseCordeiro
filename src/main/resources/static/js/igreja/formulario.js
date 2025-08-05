var camposComErro = [];
var modalElement = null;

$(document).ready(function() {
	
	modalElement = $("#mensagemModal");
	
	$("#tipo").on("change", function() {
		ajustarCamposPorTipo($(this).val());
	});

	ajustarCamposPorTipo($("#tipo").val());

	$("#btnSalvar").on("click", function(event) {
		event.preventDefault();
		if (!validarFormulario()) {
			return;
		}
		const igrejaJson = montarJson();
		enviarCadastro(igrejaJson);
	});

	$('#uf').on('blur', function() {
		const valor = $(this).val();
		$(this).val(valor.toUpperCase());
	});

	$("#btnVoltar").on("click", function() {
		$('#conteudoPrincipal').load('/igreja/index', function() {
			$.getScript('/js/igreja/index.js');
			$.getScript('/js/igreja/formulario.js');
		});
	});
	
	modalElement.on('hidden.bs.modal', function() {
		camposComErro.forEach(campo => {
			const input = $(`#${campo}`);
			input.addClass("is-invalid");
		});

		// Foca no primeiro campo com erro, se existir
		if (camposComErro.length > 0)
			$(`#${camposComErro[0]}`).focus();
		$("#mensagemErro").html("");
	});
});

function ajustarCamposPorTipo(tipo) {
	if (tipo === "FISICA") {
		$("#cpf").prop("disabled", false);
		$("#rg").prop("disabled", false);
		$("#cnpj").prop("disabled", true).val("").removeClass("is-invalid");
	} else if (tipo === "JURIDICA") {
		$("#cnpj").prop("disabled", false);
		$("#cpf").prop("disabled", true).val("").removeClass("is-invalid");
		$("#rg").prop("disabled", true).val("").removeClass("is-invalid");
	} else {
		$("#cpf, #cnpj, #rg").prop("disabled", true).val("").removeClass("is-invalid");
	}
}

// Monta o JSON da igreja
function montarJson() {
	const tipo = $("#tipo").val();

	const dadosPessoais = {
		descricao: $("#descricao").val(),
		tipo: tipo,
		dataNascimento: $("#dataNascimento").val(),
		telefone1: $("#telefone1").val(),
		telefone2: $("#telefone2").val(),
		telefone3: $("#telefone3").val(),
		email: $("#email").val(),
		endereco: {
			rua: $("#rua").val(),
			numero: $("#numero").val(),
			bairro: $("#bairro").val(),
			cidade: $("#cidade").val(),
			uf: $("#uf").val()
		}
	};

	if (tipo === "FISICA") {
		dadosPessoais.cpf = $("#cpf").val();
		dadosPessoais.rg = $("#rg").val();
	} else if (tipo === "JURIDICA") {
		dadosPessoais.cnpj = $("#cnpj").val();
	}

	return {
		ativo: $("#ativo").is(":checked"),
		dadosPessoais: dadosPessoais
	};
}


// Envia os dados com AJAX
function enviarCadastro(json) {
	$.ajax({
		url: "/api/v001/igreja", // atualize conforme sua API
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(json),
		success: function() {
			alert("Igreja cadastrada com sucesso!");
			$("#formIgreja")[0].reset();
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao cadastrar igreja.";
			if (xhr.responseJSON && xhr.responseJSON.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			$.exibirErro(mensagemErro);
		}
	});
}

$.exibirErro = function(mensagem) {
	const obj = JSON.parse(mensagem);
	camposComErro = obj.erros.map(e => e.campo);
	let frase = obj.mensagem + ": ";
	frase += obj.erros.map(e => `${e.erro} em '${e.campo}'`).join(", ");
	$("#mensagemErro").html(frase);
	const modal = new bootstrap.Modal(modalElement);
	modal.show();
}