var camposComErro = [];
var modalElement = null;
var novo = true;

$(document).ready(function() {

	$.ajustarLegenda();
	
	const alunoJson = sessionStorage.getItem("alunoEdicao");
	if (alunoJson) {
		const json = JSON.parse(alunoJson);
		$.preencherFormulario(json);

		sessionStorage.removeItem("alunoEdicao");
	}

	modalElement = $("#mensagemModal");

	ajustarCamposPorTipo($("#tipo").val());
	$("#tipo").on("change", function() {
		ajustarCamposPorTipo($(this).val());
	});

	$("#btnSalvar").on("click", function(event) {
		event.preventDefault();
		if (!$.validarFormulario()) {
			return;
		}
		const json = montarJson();
		enviarCadastro(json);
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

	$("#uf").on("change", function() {
		$(this).val($(this).val().toUpperCase());
	});

	$("#igrejaId").on("change", function() {
		$.carregaIgreja(this.value);
	});

	$.carregaIgreja(igrejaIdUsuarioLogado);
	$.carregaClubes();
	
	$("#clubeId").on("change", function() {
		$.carregaClube($(this).val());
	});

	var alunoId = $('#id').val();
    if (alunoId) {
        carregarAluno(alunoId);
        novo = false;
    } else {
        carregarClubes();
        novo = true;
    }
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

// Monta o JSON do Aluno
function montarJson() {

	const id = $("#id").val() || null;
	const tipo = $("#tipo").val();
	const igrejaId = $("#igrejaId").val() || null;
	const clubeId = $("#clubeId").val() || null;

	const dadosPessoais = {
		descricao: $("#descricao").val(),
		tipo: tipo,
		dataNascimento: $("#dataNascimento").val(),
		contato: $("#contato").val(),
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
		dadosPessoais: dadosPessoais,
		igrejaId: igrejaId,
		clubeId: clubeId
	};
}

// Envia os dados com AJAX
function enviarCadastro(json) {

	let url = novo ? "/api/v001/aluno" : "/api/v001/aluno/" + json.id;
	let type = novo ? "POST" : "PUT";

	$.ajax({
		url: url,
		type: type,
		contentType: "application/json",
		data: JSON.stringify(json),
		success: function() {
			OanseLib.exibirSucesso("Processo concluído com sucesso!");
			if (novo) {
				$("#formAluno")[0].reset();
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

// Carrega clubes via API e preenche o select
function carregarClubes(clubeIdSelecionado) {
    $.get('/api/v001/clubes', function(clubes) {
        var $select = $('#clube');
        $select.empty();
        $select.append('<option value="">Nenhum</option>');
        $.each(clubes, function(_, clube) {
            var selected = clubeIdSelecionado && clube.id == clubeIdSelecionado ? 'selected' : '';
            $select.append('<option value="' + clube.id + '" ' + selected + '>' + clube.nome + '</option>');
        });
    });
}

// Carrega aluno via API para edição
function carregarAluno(id) {
    $.get('/api/v001/aluno/' + id, function(aluno) {
        $.preencherFormulario(aluno);
        carregarClubes(aluno.clubeId);
    });
}

// Preenche o formulário com os dados do aluno
$.preencherFormulario = function(aluno) {
	$.ajustarLegenda();
    $('#id').val(aluno.id);
    $('#descricao').val(aluno.dadosPessoais.descricao);
    $('#tipo').val(aluno.dadosPessoais.tipo);
    $('#dataNascimento').val(aluno.dadosPessoais.dataNascimento);
    $('#contato').val(aluno.dadosPessoais.contato);
    $('#telefone1').val(aluno.dadosPessoais.telefone1);
    $('#telefone2').val(aluno.dadosPessoais.telefone2);
    $('#telefone3').val(aluno.dadosPessoais.telefone3);
    $('#email').val(aluno.dadosPessoais.email);
    $('#rua').val(aluno.dadosPessoais.endereco.rua);
    $('#numero').val(aluno.dadosPessoais.endereco.numero);
    $('#bairro').val(aluno.dadosPessoais.endereco.bairro);
    $('#cidade').val(aluno.dadosPessoais.endereco.cidade);
    $('#uf').val(aluno.dadosPessoais.endereco.uf);
    $('#ativo').prop('checked', aluno.ativo);
    $.carregaIgreja(aluno.igrejaId);
    $.carregaClube(aluno.clubeId);
    if (aluno.dadosPessoais.tipo === 'FISICA') {
        $('#cpf').val(aluno.dadosPessoais.cpf);
        $('#rg').val(aluno.dadosPessoais.rg);
    } else if (aluno.dadosPessoais.tipo === 'JURIDICA') {
        $('#cnpj').val(aluno.dadosPessoais.cnpj);
    }
};

$.carregaClube = function(id) {
	if (!id) {
		$("#clubeDescricao").val("");
		return;
	}
	$.ajax({
		url: "/api/v001/clube/" + id,
		type: "GET",
		success: function(data) {
			if (data) {
				$("#clubeId").val(data.id);
				$("#clubeDescricao").val(data.nome);
				$("#clubeId").removeClass("is-invalid");
				$("#clubeDescricao").removeClass("is-invalid");
			} else {
				$("#clubeId").val("").addClass("is-invalid");
				$("#clubeDescricao").val("").addClass("is-invalid");
			}
		},
		error: function() {
			$("#clubeId").val("").addClass("is-invalid");
			$("#clubeDescricao").val("").addClass("is-invalid");
		}
	});
}

$.carregaClubes = function() {	
    $("#clubeOptions").empty(); // limpa antes de preencher
    $.each(clubes, function(i, clube) {
        i++;
        $("#clubeOptions").append('<div><label>' + i + ' - ' + clube + '</label></div>');
    });
}

$.carregaIgreja = function(id) {
	if (!id) {
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
		"#uf",
		"#igrejaId",
		"#igrejaDescricao",
		"#clubeId",
		"#clubeDescricao"
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
	const dataNascimento = $('#dataNascimento').val();
	/*const rg = $('#rg').val();*/
	const telefone1 = $('#telefone1').val();
	const telefone2 = $('#telefone2').val();
	const telefone3 = $('#telefone3').val();
	const email = $('#email').val();
	const uf = $('#uf').val();
	const igrejaDescricao = $('#igrejaDescricao').val();
	const clubeDescricao = $('#clubeDescricao').val();

	if (!OanseLib.validarTexto(descricao)) {
		valido = false;
		$('#descricao').addClass("is-invalid");
	} else {
		$('#descricao').removeClass("is-invalid");
	}

	if (!OanseLib.validarTexto(igrejaDescricao)) {
		valido = false;
		$('#igrejaDescricao').addClass("is-invalid");
	} else {
		$('#igrejaDescricao').removeClass("is-invalid");
	}

	if (tipo === 'FISICA') {
		/*if (!OanseLib.validarRG(rg)) {
			valido = false;
			$('#rg').addClass("is-invalid");
		} else {
			$('#rg').removeClass("is-invalid");
		}*/
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

	if (!OanseLib.validarTexto(clubeDescricao)) {
		valido = false;
		$('#clubeDescricao').addClass("is-invalid");
	} else {
		$('#clubeDescricao').removeClass("is-invalid");
	}

	return valido;
}

$.ajustarLegenda = function() {
	if (novo) {
		$('#legendaFormulario').text('Cadastro de Aluno - NOVO');
	} else {
		$('#legendaFormulario').text('Cadastro de Aluno - EDICAO');
	}
}