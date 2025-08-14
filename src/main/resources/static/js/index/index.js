$(document).ready(function() {
    $('#btnCadastrarIgreja').click(function(e) {
        e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
        window.location.href = '/igreja/index'; // redireciona para a nova página
    });
	
    $('#btnCadastrarAluno').click(function(e) {
        e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
        window.location.href = '/aluno/index'; // redireciona para a nova página
    });
	
    $('#btnCadastrarUsuario').click(function(e) {
        e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
        window.location.href = '/usuario/index'; // redireciona para a nova página
    });
	
    $('#btnSair').click(function(e) {
        e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
        window.location.href = '/logout'; // redireciona para a nova página
    });
});
