var camposComErro = [];
var modalElement = null;
var novo = true;

$(document).ready(function() {

	const igrejaJson = sessionStorage.getItem("igrejaEdicao");
	if (igrejaJson) {
		const igreja = JSON.parse(igrejaJson);		
		$.preencherFormulario(igreja);

		// Limpa para não reutilizar depois
		sessionStorage.removeItem("igrejaEdicao");
	}

	modalElement = $("#mensagemModal");
	
	$.ajustarLegenda();

	ajustarCamposPorTipo($("#tipo").val());
	$("#tipo").on("change", function() {
		ajustarCamposPorTipo($(this).val());
	});

	$("#btnSalvar").on("click", function(event) {
		event.preventDefault();
		if (!$.validarFormulario()) {
			return;
		}
		const igrejaJson = montarJson();
		enviarCadastro(igrejaJson);
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
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
	if (!novo) 
		return;
	
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
	
	const id = $("#id").val() || null;
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
		id: id,
		ativo: $("#ativo").is(":checked"),
		dadosPessoais: dadosPessoais
	};
}


// Envia os dados com AJAX
function enviarCadastro(json) {
	
	let url = novo ? "/api/v001/igreja" : "/api/v001/igreja/" + json.id;
	let type = novo ? "POST" : "PUT";
	
	$.ajax({
		url: url,
		type: type,
		contentType: "application/json",
		data: JSON.stringify(json),
		success: function() {
			$.exibirSucesso("Processo concluído com sucesso!");
			if (novo) {
				$("#formIgreja")[0].reset();
				window.history.back();				
			}
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
	const $modal = $("#mensagemModal");
	const $modalTitle = $modal.find(".modal-title");
	const $modalBody = $modal.find(".modal-body");

	camposComErro = [];

	let fraseFinal = "";

	try {
		const obj = typeof mensagem === "string" ? JSON.parse(mensagem) : mensagem;

		if (obj && obj.erros && Array.isArray(obj.erros)) {
			camposComErro = obj.erros.map(e => e.campo);
			fraseFinal = `${obj.mensagem}:<br>`;
			fraseFinal += "<ul>" + obj.erros.map(e => `<li>${e.erro} em <strong>${e.campo}</strong></li>`).join("") + "</ul>";
		} else if (obj && obj.mensagem) {
			fraseFinal = obj.mensagem;
		} else {
			fraseFinal = JSON.stringify(obj);
		}
	} catch (e) {
		fraseFinal = mensagem; // mensagem simples
	}

	$modalTitle.text("Erro");
	$modalBody.html(`<div class="alert alert-danger">${fraseFinal}</div>`);

	const modal = new bootstrap.Modal($modal[0]);
	modal.show();
};

$.exibirSucesso = function(mensagem) {
	const $modal = $("#mensagemModal");
	const $modalTitle = $modal.find(".modal-title");
	const $modalBody = $modal.find(".modal-body");

	camposComErro = []; // limpa erros anteriores

	$modalTitle.text("Sucesso");
	$modalBody.html(`<div class="alert alert-success">${mensagem}</div>`);

	const modal = new bootstrap.Modal($modal[0]);
	modal.show();
};

$.validarFormulario = function() {
	let valido = true;

	const camposObrigatorios = [
		"#descricao",
		"#tipo",
		"#dataNascimento",
		"#telefone1",
		"#rua",
		"#bairro",
		"#cidade",
		"#uf"
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

	const descricao = $('#descricao').val();
	const tipo = $('#tipo').val();
	const cpf = $('#cpf').val();
	const cnpj = $('#cnpj').val();
	const rg = $('#rg').val();
	const email = $('#email').val();
	const telefone1 = $('#telefone1').val();
	const telefone2 = $('#telefone2').val();
	const telefone3 = $('#telefone3').val();
	const uf = $('#uf').val();
	const dataNascimento = $('#dataNascimento').val();

	if (!OanseLib.validarTexto(descricao)) {
		valido = false;
		$('#descricao').addClass("is-invalid");
	} else {
		$('#descricao').removeClass("is-invalid");
	}

	if (tipo === 'FISICA') {
		if (!OanseLib.validarRG(rg)) {
			valido = false;
			$('#rg').addClass("is-invalid");
		} else {
			$('#rg').removeClass("is-invalid");
		}
		if (!OanseLib.validarCPF(cpf)) {
			valido = false;
			$('#cpf').addClass("is-invalid");
		} else {
			$('#cpf').removeClass("is-invalid");
		}
	}

	if (tipo === 'JURIDICA' && !OanseLib.validarCNPJ(cnpj)) {
		valido = false;
		$('#cnpj').addClass("is-invalid");
	} else {
		$('#cnpj').removeClass("is-invalid");
	}

	if (email && !OanseLib.validarEmail(email)) {
		valido = false;
		$('#email').addClass("is-invalid");
	} else {
		$('#email').removeClass("is-invalid");
	}

	if (!OanseLib.validarTelefone(telefone1)) {
		valido = false;
		$('#telefone1').addClass("is-invalid");
	} else {
		$('#telefone1').removeClass("is-invalid");
	}

	if (telefone2 && !OanseLib.validarTelefone(telefone2)) {
		valido = false;
		$('#telefone2').addClass("is-invalid");
	} else {
		$('#telefone2').removeClass("is-invalid");
	}

	if (telefone3 && !OanseLib.validarTelefone(telefone3)) {
		valido = false;
		$('#telefone3').addClass("is-invalid");
	} else {
		$('#telefone3').removeClass("is-invalid");
	}

	if (!OanseLib.validarUF(uf)) {
		valido = false;
		$('#uf').addClass("is-invalid");
	} else {
		$('#uf').removeClass("is-invalid");
	}

	if (!OanseLib.validarDataNascimento(dataNascimento)) {
		valido = false;
		$('#dataNascimento').addClass("is-invalid");
	} else {
		$('#dataNascimento').removeClass("is-invalid");
	}

	return valido;
}

$.ajustarLegenda = function() {
	if (novo) {
		$('#legendaFormulario').text('Cadastro de Igreja - NOVO');
    } else {
		$('#legendaFormulario').text('Cadastro de Igreja - EDICAO');
    }
}

$.preencherFormulario = function(igreja) {
	if (!igreja || !igreja.dadosPessoais) return;
	
	novo = false;

	const dados = igreja.dadosPessoais;
	const endereco = dados.endereco || {};
	
	$("#id").val(igreja.id || '');
	$("#descricao").val(dados.descricao || '');
	$("#tipo").val(dados.tipo || '');	
	$("#rg").val(dados.rg || '');	
	$("#cpf").val(dados.cpf || '');	
	$("#cnpj").val(dados.cnpj || '');
	$("#dataNascimento").val(dados.dataNascimento || '');
	$("#telefone1").val(dados.telefone1 || '');
	$("#telefone2").val(dados.telefone2 || '');
	$("#telefone3").val(dados.telefone3 || '');
	$("#email").val(dados.email || '');

	$("#rua").val(endereco.rua || '');
	$("#numero").val(endereco.numero || '');
	$("#bairro").val(endereco.bairro || '');
	$("#cidade").val(endereco.cidade || '');
	$("#uf").val(endereco.uf || '');

	$("#ativo").prop("checked", !!igreja.ativo);
	
	$("#rg, #cpf, #cnpj").prop("disabled", true);
}
