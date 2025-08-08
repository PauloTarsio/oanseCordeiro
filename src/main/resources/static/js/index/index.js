$(document).ready(function() {
    $('#btnCadastrarIgreja').click(function(e) {
        e.preventDefault(); // evita comportamento padrão se for um botão dentro de um <form>
        window.location.href = '/igreja/index'; // redireciona para a nova página
    });
});
