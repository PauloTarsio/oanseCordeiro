let configPesquisa = {};
let callbackSelecao = null;

/* ABRIR MODAL */
function abrirPesquisa(config, callback) {

	configPesquisa = config;
	callbackSelecao = callback;

	$("#tituloModalPesquisa").text(config.titulo);
	$("#campoPesquisa").val("");

	/* LIMPA GRID SE EXISTIR */
	if ($("#gridPesquisa")[0].grid) {
		$("#gridPesquisa").jqGrid("clearGridData");
	}

	$("#modalPesquisa").modal("show");
}

/* CRIA GRID QUANDO MODAL ABRE */
$("#modalPesquisa").on("shown.bs.modal", function () {
	montarGrid();
});

/* MONTAR GRID */
function montarGrid() {

	if ($("#gridPesquisa")[0].grid) {
		$("#gridPesquisa").jqGrid("GridUnload");
	}

	$("#gridPesquisa").jqGrid({

		url: configPesquisa.url,
		mtype: "GET",

		/* NÃO PESQUISA AO ABRIR */
		datatype: "local",

		postData: {
			descricao: function () {
				return $("#campoPesquisa").val();
			}
		},

		colModel: configPesquisa.colunas.map(c => ({
			label: c.label,
			name: c.field,
			width: c.width || 200,
			align: c.align || "left",
			labelAlign: c.labelAlign || "left"
		})),

		/* SUPORTE A LISTA JSON */
		jsonReader: {
			root: function (obj) { return obj; },
			repeatitems: false,
			id: "id"
		},

		viewrecords: true,
		height: 200,
		autowidth: true,
		shrinkToFit: true,

		/* DUPLO CLIQUE SELECIONA */
		ondblClickRow: function (id) {

			const row = $("#gridPesquisa").jqGrid("getRowData", id);

			$("#modalPesquisa").modal("hide");

			if (callbackSelecao) {
				callbackSelecao(row);
			}
		},

		/* LOG DE ERRO */
		loadError: function (xhr) {
			console.error("Erro ao carregar pesquisa:", xhr.responseText);
		}
	});
}

/* BOTÃO PESQUISAR */
$("#btnExecutarPesquisa").click(function () {

	$("#gridPesquisa")
		.setGridParam({
			datatype: "json"
		})
		.trigger("reloadGrid");

});

/* ENTER NO CAMPO PESQUISA */
$("#campoPesquisa").keypress(function (e) {

	if (e.which === 13) {

		$("#gridPesquisa")
			.setGridParam({
				datatype: "json"
			})
			.trigger("reloadGrid");

	}

});