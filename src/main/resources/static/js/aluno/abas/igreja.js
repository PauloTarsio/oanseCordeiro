$(document).ready(function () {
	$("#btnPesquisaIgreja").click(function () {
		abrirPesquisa({
            titulo: "Pesquisa de Igrejas",
            url: "/api/v001/igreja",
            colunas: [
                { label: "Código", field: "id", width: 80, align: "center", labelAlign: "center" },
                { label: "Nome", field: "descricao", width: 200 }
            ]
        }, selecionarIgreja);
    });
});

function selecionarIgreja(igreja) {
	$("#igrejaId").val(igreja.id);
	$("#igrejaDescricao").val(igreja.descricao);
}