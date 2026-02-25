$(document).ready(function() {
	$('#btnCadastrarIgreja').click(function(e) {
		e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
		window.location.href = '/igreja/index'; // redireciona para a nova página
	});

	$('#btnCadastrarAluno').click(function(e) {
		e.preventDefault();
		window.location.href = '/aluno/index';
	});

	$('#btnCadastrarUsuario').click(function(e) {
		e.preventDefault();
		window.location.href = '/usuario/index';			
	});

	$('#btnSair').click(function(e) {
		e.preventDefault();
		window.location.href = '/logout';
	});
});
