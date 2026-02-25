var camposComErro = [];
var modalElement = null;
var novo = true;

$(document).ready(function() {

	const alunoJson = sessionStorage.getItem("alunoEdicao");
	if (alunoJson) {
		const json = JSON.parse(alunoJson);
		$.preencherFormulario(json);
		sessionStorage.removeItem("alunoEdicao");
	}

	$.ajustarLegenda();

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
	//$.carregaClubes();
	
	/*$("#clubeId").on("change", function() {
		$.carregaClube($(this).val());
	});*/

});

// --- ABA MANUAL ---
// Carrega combo de manuais
/*$.carregarComboManuais = function() {
    $.ajax({
        url: "/api/v001/manuais", // endpoint que retorna a lista de manuais
        type: "GET",
        success: function(manuais) {
            var $combo = $("#manualSelect");
            $combo.empty();
            $combo.append('<option value="">Selecione um manual</option>');
            manuais.forEach(function(manual) {
                $combo.append($('<option>', {
                    value: manual.id,
                    text: manual.nome
                }));
            });
        },
        error: function() {
            OanseLib.exibirErro("Erro ao carregar manuais.");
        }
    });
};*/

// Associa manual ao aluno
/*$("#btnAssociarManual").on("click", function() {
    const manualId = $("#manualSelect").val();
    const alunoId = $('#id').val();
    if (!manualId) {
        OanseLib.exibirErro("Selecione um manual.");
        return;
    }
	if (!alunoId) {
        OanseLib.exibirErro("Aluno não identificado. Salve o aluno antes de associar um manual.");
		return;
	}
    $.ajax({
        url: "/api/v001/aluno-manual?alunoId=" + alunoId + "&livroId=" + manualId,
        type: "POST",
        success: function() {
			OanseLib.exibirMensagem("Manual associado com sucesso!");
        },
        error: function(xhr) {
            let mensagemErro = "Erro ao associar manual.";
            if (xhr.responseJSON?.message) {
                mensagemErro = xhr.responseJSON.message;
            } else if (xhr.responseText) {
                mensagemErro = xhr.responseText;
            }
            OanseLib.exibirErro(mensagemErro);
        }
    });
});*/

// --- ABA MANUAL ---
// Carrega combo de manuais do clube
/*$.carregarComboManuaisDoClube = function(clubeId) {
	var clube = clubes[clubeId-1];
    var $combo = $("#manualSelect");
    $combo.empty();
    $combo.append('<option value="">Selecione um manual</option>');
    if (!clube) {
        $combo.prop('disabled', true);
        $("#manualInfo").html('<div class="alert alert-warning">Escolha um Clube antes de associar um Manual.</div>');
        return;
    }
    $combo.prop('disabled', false);
    $.ajax({
        url: "/api/manuais?clube=" + clube,
        type: "GET",
        success: function(manuais) {
            if (manuais && manuais.length > 0) {
                manuais.forEach(function(manual) {
                    $combo.append($('<option>', {
                        value: manual.id,
                        text: manual.descricao
                    }));
                });
            } else {
                $combo.append('<option value="">Nenhum manual disponível para o clube</option>');
            }
        },
        error: function() {
            OanseLib.exibirErro("Erro ao carregar manuais do clube.");
        }
    });
};
*/
// Ao abrir a aba Manual, verifica se há clube e carrega combo
/*$("#manual-tab").on("shown.bs.tab", function() {
    var clubeId = $("#clubeId").val();
    if (!clubeId) {
        $.carregarComboManuaisDoClube(null);
        return;
    }
    $.carregarComboManuaisDoClube(clubeId);
    const alunoId = $("#formAluno").data("aluno-id");
    if (alunoId) {
        $.carregarManualAluno(alunoId);
    }
});*/

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

	var json = {
		id: id,
		ativo: $("#ativo").is(":checked"),
		dadosPessoais: dadosPessoais,
		igrejaId: igrejaId,
		clubeId: clubeId,
		fotoBase64: window.fotoBase64 || ""
	};

	return json;
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

// Preenche o formulário com os dados do aluno
$.preencherFormulario = function(aluno) {
	novo = false;
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
    //$.carregaClube(aluno.clubeId);
    if (aluno.dadosPessoais.tipo === 'FISICA') {
        $('#cpf').val(aluno.dadosPessoais.cpf);
        $('#rg').val(aluno.dadosPessoais.rg);
    } else if (aluno.dadosPessoais.tipo === 'JURIDICA') {
        $('#cnpj').val(aluno.dadosPessoais.cnpj);
    }
	preencherFotoAluno(aluno.fotoBase64);
};

function preencherFotoAluno(fotoBase64) {
    if (fotoBase64 && fotoBase64.length > 0) {
        $("#imgPreview").attr("src", "data:image/png;base64," + fotoBase64).show();
        $("#removerFoto").show();
        window.fotoBase64 = fotoBase64;
    } else {
        $("#imgPreview").attr("src", "").hide();
        $("#removerFoto").hide();
        window.fotoBase64 = "";
    }
}

/*$.carregaClube = function(id) {
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
*/
/*$.carregaClubes = function() {	
    $("#clubeOptions").empty(); // limpa antes de preencher
    $.each(clubes, function(i, clube) {
        i++;
        $("#clubeOptions").append('<div><label>' + i + ' - ' + clube + '</label></div>');
    });
}*/

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
		//"#clubeId",
		//"#clubeDescricao"
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
	//const clubeDescricao = $('#clubeDescricao').val();

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

window.fotoBase64 = "";
$("#fotoAluno").on("change", function(e) {
    const file = e.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(evt) {
            window.fotoBase64 = evt.target.result.split(",")[1];
            $("#imgPreview").attr("src", evt.target.result).show();
            $("#removerFoto").show();
        };
        reader.readAsDataURL(file);
    }
});
$("#removerFoto").on("click", function() {
    window.fotoBase64 = "";
    $("#imgPreview").attr("src", "").hide();
    $("#fotoAluno").val("");
    $("#removerFoto").hide();
});

// Area sessao