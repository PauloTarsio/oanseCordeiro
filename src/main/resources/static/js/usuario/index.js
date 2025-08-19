$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'DESCRICAO', name: 'descricao', align: 'left' }
		],
		rowNum: 10,
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
	
	// Botão Pesquisar
	$("#btnPesquisar").on("click", function() {
		$.pesquisar();
	});

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
			window.location.href = "/usuario/formulario";
		},
		error: function(xhr) {
			if (xhr.status === 403) {
				OanseLib.exibirErro("Você não tem permissão para acessar esta funcionalidade.");
			}
		}
	});
}

$.pesquisar = function() {
	const filtro = $("#filtroDescricao").val().toLowerCase();
	
	console.log("Filtro de pesquisa:", filtro);
/*
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
			exibirErro(mensagemErro);
		}
	});*/
}

$.editar = function(id) {
	if (!isAdmin) {
		OanseLib.exibirErro("Você não tem permissão para acessar esta funcionalidade.");
		return;
	}
	let url = "/api/v001/igreja/" + id;
	
	console.log("Editando registro com ID:", id);

	/*$.ajax({
		url: url,
		type: "GET",
		success: function(data) {
			if (data) {
				sessionStorage.setItem("igrejaEdicao", JSON.stringify(data));
				window.location.href = '/igreja/formulario';
			} else {
				exibirErro("Registro não encontrado.");
			}
		},
		error: function(xhr) {
			let mensagemErro = "Erro ao buscar registro.";
			if (xhr.responseJSON?.message) {
				mensagemErro = xhr.responseJSON.message;
			} else if (xhr.responseText) {
				mensagemErro = xhr.responseText;
			}
			exibirErro(mensagemErro);
		}
	});*/
}