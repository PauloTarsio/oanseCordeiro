$(document).ready(function() {
	function handleRedirect(url) {
		OanseLib.exibirLoading();
		window.location.href = url;
	}

	$('#btnCadastrarIgreja').click(function(e) {
		e.preventDefault();
		handleRedirect('/igreja/index');
	});
	$('#btnCadastrarAluno').click(function(e) {
		e.preventDefault();
		handleRedirect('/aluno/index');
	});
	$('#btnCadastrarUsuario').click(function(e) {
		e.preventDefault();
		handleRedirect('/usuario/index');
	});
	$('#btnAtribuiManual').click(function(e) {
		e.preventDefault();
		handleRedirect('/aluno-manual/index');
	});
	$('#btnSair').click(function(e) {
		e.preventDefault();
		handleRedirect('/logout');
	});

});

// Exibe o loading em todas as requisições AJAX
$(document).ajaxStart(function() {
	OanseLib.exibirLoading();
});
$(document).ajaxStop(function() {
	OanseLib.esconderLoading();
});