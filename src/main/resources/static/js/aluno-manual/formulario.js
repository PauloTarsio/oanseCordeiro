$(document).ready(function() {

	$('.number').on('input', function() {
    	this.value = this.value.replace(/[^0-9]/g, '');
	});

	$.escreverLegenda();
});	

$.escreverLegenda = function(){
	$('#legendaFormulario').text('Associar aluno com manual');
}