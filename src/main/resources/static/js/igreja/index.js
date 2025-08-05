$(document).ready(function() {

	// Inicializa jqGrid
	$("#jqGrid").jqGrid({
		data: [],
		datatype: "local",
		colModel: [
			{ label: 'ID', name: 'id', width: 30, key: true, align: 'center' },
			{ label: 'DESCRICAO', name: 'descricao', align: 'left' },
			{ label: 'ATIVO', name: 'ativo', formatter: booleanFormatter, align: 'center' },
		],
		rowNum: 10,
		viewrecords: true,
		hidegrid: false,
		autowidth: true, // faz o grid ajustar à largura do contêiner pai
		shrinkToFit: true, // garante que as colunas se ajustem dentro da largura
		caption: "Registros"
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
		const filtro = $("#filtroDescricao").val().toLowerCase();

		let url = (filtro.trim() === "") ?
			"/api/v001/igreja" :
			"/api/v001/igreja?descricao=" + encodeURIComponent(filtro);

		$.ajax({
			url: url,
			type: "GET",
			success: function(data) {
				carregarGrid(data);
				cleanFilter();
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
		});
	});

	// Limpa o campo de filtro
	function cleanFilter() {
		$("#filtroDescricao").val("");
	}

	// Botão Incluir
	$("#btnIncluir").on("click", function() {
		$('#conteudoPrincipal').load('/igreja/formulario', function() {
			$.getScript('/js/validarFormulario.js');
			$.getScript('/js/igreja/formulario.js');
		});
	});

	// Carrega os dados na grid
	function carregarGrid(data) {
		$("#jqGrid").jqGrid("clearGridData");
		$("#jqGrid").jqGrid("setGridParam", { data: data });
		$("#jqGrid").trigger("reloadGrid");
	}

});
