let alunoSelecionado = null;

$(document).ready(function () {
	/* VOLTAR */
	$("#btnVoltar").click(function () {
		window.history.back();
	});

	/* ABRIR PESQUISA DE ALUNO */
	$("#btnPesquisaAluno").click(function () {
		abrirPesquisa({
			titulo: "Pesquisa de Alunos",
			url: "/api/v001/aluno",
			colunas: [
				{ label: "Código", field: "id", width: 80, align: "center", labelAlign: "center" },
				{ label: "Nome", field: "descricao", width: 200 },
				{ label: "Clube", field: "clube", width: 90 },
				/*{ label: "Igreja", field: "igreja", width: 200 }*/
			]
		}, selecionarAluno);
	});
});

/* QUANDO SELECIONA ALUNO */
function selecionarAluno(aluno) {
	alunoSelecionado = aluno;
	$("#codigoAluno").val(aluno.id);
	$("#descricaoAluno").val(aluno.descricao);
	$("#nomeClube").val(aluno.clube);	
	carregarLivros(aluno.clube);
}

/* CARREGAR MANUAIS */
function carregarLivros(clube) {
	$.ajax({
		url: "/api/v001/manuais",
		type: "GET",
		data: { clube: clube },
		success: function (livros) {
			if (!livros || livros.length === 0) {
				OanseLib.exibirErro("Nenhum livro encontrado para o clube " + clube);
				return;
			}
			let options = "<option value=''>Selecione</option>";
			$.each(livros, function (index, livro) {
				options += `<option value="${livro}">${livro}</option>`;
			});
			$("#comboLivro").html(options);
		},
		error: function () {
			OanseLib.exibirErro("Erro ao carregar os livros.");
		}
	});
}