$(document).ready(function() {

	$('#btnCadastrarIgreja').click(function(e) {
		$.gerenciarIgrejas(e);
	});

});

$.gerenciarIgrejas = function(e) {
	$('#conteudoPrincipal').load('/igreja/index', function() {
		$.getScript('/js/igreja/index.js');
		$.getScript('/js/igreja/formulario.js');
	});
}