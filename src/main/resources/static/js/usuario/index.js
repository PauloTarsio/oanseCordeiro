$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'UUID', name: 'id', align: 'center' },
			{ label: 'LOGIN', name: 'login', align: 'center' },
			{ label: 'PERFIL', name: 'perfil', align: 'center' },
			{ label: 'IGREJA', name: 'igrejaDescricao', align: 'center' }
		],
		rowNum: 10,
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
			if (idSelecionado) {
				$.editar(idSelecionado);
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

	// Botão Pesquisar
	$("#btnPesquisar").on("click", function() {
		$.pesquisar();
	});

	$("#btnIncluir").on("click", function() {
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
			window.location.href = "/usuario/formulario";
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

$.pesquisar = function() {
	const filtro = $("#filtroDescricao").val().toLowerCase();

	let url = (filtro.trim() === "") ?
		"/api/v001/usuario" :
		"/api/v001/usuario?descricao=" + encodeURIComponent(filtro);

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

// Carrega os dados na grid
$.carregarGrid = function(data) {
	$("#jqGrid").jqGrid("clearGridData");
	$("#jqGrid").jqGrid("setGridParam", { data: data });
	$("#jqGrid").trigger("reloadGrid");
}

// Limpa o campo de filtro
$.cleanFilter = function() {
	$("#filtroDescricao").val("");
}

$.editar = function(id) {
	let url = "/api/v001/usuario/" + id;
	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			if (data) {
				sessionStorage.setItem("usuarioEdicao", JSON.stringify(data));
				window.location.href = '/usuario/formulario';
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