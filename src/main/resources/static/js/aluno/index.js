var aluno = {}

$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'ID', name: 'id', width: 30, key: true, align: 'center' },
			{ label: 'NOME', name: 'descricao', align: 'center' },
			{ label: 'CLUBE', name: 'clube', align: 'center' },
			{ label: 'IGREJA', name: 'igreja', align: 'center' },
			{ label: 'ATIVO', name: 'ativo', width: 30, formatter: booleanFormatter, align: 'center' },
		],
		rowNum: 50,
		height: 300,
		viewrecords: true,
		hidegrid: false,
		autowidth: true, // faz o grid ajustar à largura do contêiner pai
		shrinkToFit: true, // garante que as colunas se ajustem dentro da largura
		beforeSelectRow: function(rowid) {
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
		title: "Editar registro selecionado",
		id: "btnEditarRodape",
		onClickButton: function() {
			if ($("#btnEditarRodape").hasClass("ui-state-disabled"))
		       return;
			const idSelecionado = $("#jqGrid").jqGrid("getGridParam", "selrow");
			if (idSelecionado)
				aluno.edita(idSelecionado);
			else
			    OanseLib.exibirErro("Selecione um registro para continuar.");
		},
		position: "last"
	});
	
	// Aplica estilo Bootstrap
	$("#btnEditarRodape")
		.removeClass("ui-button ui-corner-all ui-state-default")
		.addClass("btn btn-secondary");
	
	// Inicialmente desabilita o botão
	$("#btnEditarRodape").addClass("ui-state-disabled");

	// Habilita/desabilita os botões conforme seleção de linha
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
		aluno.pesquisa();
	});

	// Botão Incluir
	$("#btnIncluir").on("click", function() {
		aluno.novo();
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});

});

aluno.novo = function() {
	window.location.href = "/aluno/formulario";
}

// Carrega os dados na grid
$.carregarGrid = function(data) {
	$("#jqGrid")
		.jqGrid("clearGridData")
		.jqGrid("setGridParam", { data: data })
		.trigger("reloadGrid");
}

aluno.pesquisa = function() {
	const filtroDescricao = $("#filtroDescricao").val().toLowerCase();
	let url = "/api/v001/aluno?descricao=" + encodeURIComponent(filtroDescricao);

	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			$.carregarGrid(data);
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao buscar alunos.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			OanseLib.exibirErro(mensagemErro);
		}
	});
}

aluno.edita = function(id) {
	let url = "/api/v001/aluno/" + id;
	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			if (data) {
				sessionStorage.setItem("alunoEdicao", JSON.stringify(data));
				window.location.href = '/aluno/formulario';
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