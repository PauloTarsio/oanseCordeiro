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
		viewrecords: true,
		hidegrid: false,
		autowidth: true, // faz o grid ajustar à largura do contêiner pai
		shrinkToFit: true, // garante que as colunas se ajustem dentro da largura
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
			if (idSelecionado) {
				if (isAdmin) {
					$.editar(idSelecionado);
				} else {
					OanseLib.exibirErro("Você não tem permissão para acessar esta funcionalidade.");
					return;					
				}
			}
		},
		position: "last"
	});

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
		$.pesquisar();
	});

	// Botão Incluir
	$("#btnIncluir").on("click", function() {
		if (!isAdmin) {
			OanseLib.exibirErro("Você não tem permissão para acessar esta funcionalidade.");
			return;
		}
		$.novo();
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});

});

$.novo = function() {
	$.ajax({
		url: "/usuario/formulario",
		type: "GET",
		success: function() {
			window.location.href = '/igreja/formulario'; // redireciona para a nova página
		},
		error: function(xhr) {
			if (xhr.status === 403) {
				OanseLib.exibirErro("Você não tem permissão para acessar esta funcionalidade.");
			}
		}
	});
}

// Limpa o campo de filtro
$.cleanFilter = function() {
	$("#filtroDescricao").val("");
}

// Carrega os dados na grid
$.carregarGrid = function(data) {
	$("#jqGrid").jqGrid("clearGridData");
	$("#jqGrid").jqGrid("setGridParam", { data: data });
	$("#jqGrid").trigger("reloadGrid");
}

$.pesquisar = function() {
	const filtro = $("#filtroDescricao").val().toLowerCase();

	let url = (filtro.trim() === "") ?
		"/api/v001/igreja" :
		"/api/v001/igreja?descricao=" + encodeURIComponent(filtro);

	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			$.carregarGrid(data);
			$.cleanFilter();
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

$.editar = function(id) {
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
}