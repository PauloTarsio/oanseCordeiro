var alunoManual = {};

$(document).ready(function() {

    // Inicializa jqGrid
    $("#jqGrid").jqGrid({
        data: [],
        datatype: "local",
        colModel: [
			{ label: 'ID', name: 'id', width: 30, key: true, align: 'center' },
            { label: 'NOME', name: 'aluno', align: 'center' },
            { label: 'CLUBE', name: 'clube', align: 'center' },
            { label: 'MANUAL', name: 'livro', align: 'center' },
            { label: 'CONCLUIDO', name: 'concluido', formatter: booleanFormatter, align: 'center' },
            { label: 'IGREJA', name: 'igreja', align: 'center' },
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
		alunoManual.pesquisa();
	});

	// Botão Incluir
	$("#btnIniciaManual").on("click", function() {
		alunoManual.novo();
	});
	
	$("#btnVoltar").on("click", function() {
		window.history.back();
	});
	
});

// ------------- Grid -----------------------

// Carrega os dados na grid
$.carregarGrid = function(data) {
	$("#jqGrid")
		.jqGrid("clearGridData")
		.jqGrid("setGridParam", { data: data })
		.trigger("reloadGrid");
}

// ------------- Ações -----------------------

alunoManual.pesquisa = function() {
	const filtroDescricao = $("#filtroDescricao").val().toLowerCase();
	let url = "/api/v001/aluno-manual?descricao=" + encodeURIComponent(filtroDescricao);

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

alunoManual.novo = function() {
    window.location.href = "/aluno-manual/formulario";
}
