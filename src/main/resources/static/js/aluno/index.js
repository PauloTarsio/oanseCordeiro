$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'ID', name: 'id', width: 30, key: true, align: 'center' },
			{ label: 'DESCRICAO', name: 'descricao', align: 'center' },
			{ label: 'CLUBE', name: 'clube', align: 'center' },
			{ label: 'IGREJA', name: 'igreja', align: 'center' },
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
			if (idSelecionado)
				$.editar(idSelecionado);
			else
			    OanseLib.exibirErro("Selecione um registro para continuar.");
		},
		position: "last"
	});
	
	// Adiciona botão do manual com ícone de livro
	$("#jqGrid").jqGrid('navButtonAdd', '#jqGridPager', {
		caption: "Manual",
		buttonicon: "ui-icon-bookmark", // ícone de livro (padrão jQuery UI)
		title: "Manual",
		id: "btnManualRodape",
		onClickButton: function() {
			const idSelecionado = $("#jqGrid").jqGrid("getGridParam", "selrow");
			if (idSelecionado)
				console.log(idSelecionado);
			else
                OanseLib.exibirErro("Selecione um registro para continuar.");
		},
		position: "last"
	});

		// Inicialmente desabilita o botão
	$("#btnEditarRodape").addClass("ui-state-disabled");
	$("#btnManualRodape").addClass("ui-state-disabled");

	// Habilita o botão quando uma linha é selecionada
	$("#jqGrid").on("jqGridSelectRow", function() {
		$("#btnEditarRodape").removeClass("ui-state-disabled");
		$("#btnManualRodape").removeClass("ui-state-disabled");
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
		$.novo();
	});

	$("#btnVoltar").on("click", function() {
		window.history.back();
	});

	$.carregaClubes();
});

$.carregaClubes = function() {	
	$.each(clubes, function(i, clube) {
		i++;
		$("#filtroClube").append('<option value="' + i + '">' + clube + '</option>');
	});
}

$.novo = function() {
	$.ajax({
		url: "/aluno/formulario",
		type: "GET",
		success: function() {
			window.location.href = "/aluno/formulario";
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
	const filtroDescricao = $("#filtroDescricao").val().toLowerCase();
	const filtroClubeId = $("#filtroClube").val();

	let params = [];
	if (filtroDescricao.trim() !== "") {
		params.push("descricao=" + encodeURIComponent(filtroDescricao));
	}
	if (filtroClubeId && filtroClubeId !== "Todos os clubes") {
		params.push("clubeId=" + encodeURIComponent(filtroClubeId));
	} else if (filtroClubeId === "Todos os clubes") {
		params.push("clubeId=");
	}

	let url = "/api/v001/aluno";
	if (params.length > 0) {
		url += "?" + params.join("&");
	}

	$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			$.carregarGrid(data);
			$.cleanFilter();
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

$.editar = function(id) {
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