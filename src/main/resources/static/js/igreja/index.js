var igreja = {};

$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'ID', name: 'id', width: 30, key: true, align: 'center' },
			{ label: 'DESCRICAO', name: 'descricao', align: 'center' },
			{ label: 'ATIVO', name: 'ativo', width: 30, formatter: booleanFormatter, align: 'center' },
		],
		rowNum: 50,
		height: 300,
		viewrecords: true,
		hidegrid: false,
		autowidth: true, // faz o grid ajustar à largura do contêiner pai
		shrinkToFit: true, // garante que as colunas se ajustem dentro da largura
		beforeSelectRow: function(rowid, e) {
			var selRow = $(this).jqGrid("getGridParam", "selrow");
			if (selRow === rowid) { return false; } return true; },
		pager: "#jqGridPager",
		caption: "Registros"
	});

	// Inicializa a barra de navegação (sem os botões padrão)
	$("#jqGrid").jqGrid('navGrid', '#jqGridPager', { edit: false, add: false, del: false });

	// Adiciona botão de edição com ícone de caneta
	$("#jqGrid").jqGrid('navButtonAdd', '#jqGridPager', {
		caption: "Editar",
		buttonicon: "ui-icon-pencil", // ícone de caneta (padrão jQuery UI)
		title: "Editar registro selecionado",
		id: "btnEditarRodape",
		onClickButton: function() {
			const idSelecionado = $("#jqGrid").jqGrid("getGridParam", "selrow");
			if (idSelecionado)
				igreja.edita(idSelecionado);
		},
		position: "last"
	});
	
	// Aplica estilo Bootstrap
	$("#btnEditarRodape")
		.removeClass("ui-button ui-corner-all ui-state-default")
		.addClass("btn btn-secondary");

	// Inicialmente desabilita o botão
	$("#btnEditarRodape").addClass("ui-state-disabled");

	// Habilita o botão quando uma linha é selecionada
	$("#jqGrid").on("jqGridSelectRow", function() {
		$("#btnEditarRodape").removeClass("ui-state-disabled");
	});

	$(window).on('resize', function() {
		const novaLargura = $("#tabelaRegistros").width();
		$("#jqGrid").jqGrid('setGridWidth', novaLargura);
	});

	// Formatter para booleano
	function booleanFormatter(cellValue) {
		return cellValue ? 'Sim' : 'Não';
	}

	// Botão Pesquisar
	$("#btnPesquisar").on("click", function() {
		igreja.pesquisa();
	});

	// Botão Incluir
	$("#btnIncluir").on("click", function() {
		if (perfil === 'ADMIN')
			igreja.novo();
		else
            OanseLib.exibirErro("Acesso negado.");
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});
	
	// Esconde o loading ao carregar a página
	OanseLib.esconderLoading();

});

igreja.novo = function() {
	$.ajax({
		url: "/igreja/formulario",
		type: "GET",
		success: function() {
			window.location.href = '/igreja/formulario';
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao carregar formulário.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}

// Carrega os dados na grid
$.carregarGrid = function(data) {
	$("#jqGrid").jqGrid("clearGridData");
	$("#jqGrid").jqGrid("setGridParam", { data: data });
	$("#jqGrid").trigger("reloadGrid");
}

igreja.pesquisa = function() {
	const filtro = $("#filtroDescricao").val().toLowerCase();

	let url = (filtro.trim() === "") ?
		"/api/v001/igreja" :
		"/api/v001/igreja?descricao=" + encodeURIComponent(filtro);

	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			$.carregarGrid(data);
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao buscar igrejas.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}

igreja.edita = function(id) {
	$.ajax({
		url: "/igreja/formulario",
		type: "GET",
		success: function(data) {			
			let url = "/api/v001/igreja/" + id;
			$.ajax({
				url: url,
				type: "GET",
				success: function(data) {
					if (data) {
						sessionStorage.setItem("igrejaEdicao", JSON.stringify(data));
						window.location.href = '/igreja/formulario';
					} else {
						OanseLib.exibirErro("Registro não encontrado.");
					}
				},
				error: function(xhr) {
					let mensagemErro = "Erro ao buscar registro.";
					if (xhr.responseJSON?.message) {
						mensagemErro = xhr.responseJSON.message;
					} else if (xhr.responseText) {
						mensagemErro = xhr.responseText;
					}
					OanseLib.exibirErro(mensagemErro);
				}
			});
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao buscar registro.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}