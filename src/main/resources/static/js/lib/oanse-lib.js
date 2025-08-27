/**
 * Biblioteca de validações Oanse para formulários
 * Valida campos brasileiros como CPF, CNPJ, RG, e-mail, telefone e UF.
 */

var perfis = ['ROLE_ADMIN', 'ROLE_SECRETARIO', 'ROLE_LIDER'];

const OanseLib = (() => {

	// Lista de UFs válidas
	const UFs = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA',
		'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN',
		'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];

	// Valida CPF
	function validarCPF(cpf) {
		cpf = cpf.replace(/[^\d]+/g, '');
		if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;

		let soma = 0;
		for (let i = 0; i < 9; i++) {
			soma += parseInt(cpf.charAt(i)) * (10 - i);
		}
		let resto = soma % 11;
		let digito1 = (resto < 2) ? 0 : 11 - resto;
		if (digito1 !== parseInt(cpf.charAt(9))) return false;

		soma = 0;
		for (let i = 0; i < 10; i++) {
			soma += parseInt(cpf.charAt(i)) * (11 - i);
		}
		resto = soma % 11;
		let digito2 = (resto < 2) ? 0 : 11 - resto;

		return digito2 === parseInt(cpf.charAt(10));
	}

	// Valida CNPJ
	function validarCNPJ(cnpj) {
		cnpj = cnpj.replace(/[^\d]+/g, '');
		if (cnpj.length !== 14 || /^(\d)\1+$/.test(cnpj)) return false;

		let tamanho = cnpj.length - 2;
		let numeros = cnpj.substring(0, tamanho);
		let digitos = cnpj.substring(tamanho);
		let soma = 0;
		let pos = tamanho - 7;
		for (let i = tamanho; i >= 1; i--) {
			soma += parseInt(numeros.charAt(tamanho - i)) * pos--;
			if (pos < 2) pos = 9;
		}
		let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
		if (resultado !== parseInt(digitos.charAt(0))) return false;

		tamanho++;
		numeros = cnpj.substring(0, tamanho);
		soma = 0;
		pos = tamanho - 7;
		for (let i = tamanho; i >= 1; i--) {
			soma += parseInt(numeros.charAt(tamanho - i)) * pos--;
			if (pos < 2) pos = 9;
		}
		resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

		return resultado === parseInt(digitos.charAt(1));
	}

	// Valida RG (mínimo 5, máximo 14 caracteres alfanuméricos)
	function validarRG(rg) {
		if (!rg) return false;		
		rg = rg.replace(/[^\w\d]+/g, '');
		return rg.length >= 5 && rg.length <= 14;
	}

	// Valida e-mail
	function validarEmail(email) {
		const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return regex.test(email);
	}

	// Valida telefone (mínimo 10 e máximo 11 dígitos)
	function validarTelefone(tel) {
		const limpo = tel.replace(/\D/g, '');
		return limpo.length >= 10 && limpo.length <= 11;
	}

	// Valida UF
	function validarUF(uf) {
		return UFs.includes(uf.toUpperCase());
	}

	// Valida data de nascimento (tem que ser passada e plausível)
	function validarDataNascimento(data) {
		const hoje = new Date();
		const nascimento = new Date(data);
		return nascimento < hoje && nascimento.getFullYear() > 1900;
	}

	// Valida campos de texto com tamanho mínimo
	function validarTexto(texto, tamanhoMin = 2) {
		return texto && texto.trim().length >= tamanhoMin;
	}

	// Exibe mensagem de sucesso em modal
	function exibirSucesso(mensagem) {
		const $modal = $("#mensagemModal");
		const $modalTitle = $modal.find(".modal-title");
		const $modalBody = $modal.find(".modal-body");

		camposComErro = []; // limpa erros anteriores

		$modalTitle.text("Sucesso");
		$modalBody.html(`<div class="alert alert-success">${mensagem}</div>`);

		const modal = new bootstrap.Modal($modal[0]);
		modal.show();

		setTimeout(() => {
			modal.hide();
		}, 2000);
	}

	// Exibe mensagem de erro em modal
	function exibirErro(mensagem) {
		const $modal = $("#mensagemModal");
		const $modalTitle = $modal.find(".modal-title");
		const $modalBody = $modal.find(".modal-body");

		camposComErro = [];

		let fraseFinal = "";

		try {
			const obj = typeof mensagem === "string" ? JSON.parse(mensagem) : mensagem;

			if (obj && obj.erros && Array.isArray(obj.erros)) {
				camposComErro = obj.erros.map(e => e.campo);
				fraseFinal = `${obj.mensagem}:<br>`;
				fraseFinal += "<ul>" + obj.erros.map(e => `<li>${e.erro} em <strong>${e.campo}</strong></li>`).join("") + "</ul>";
			} else if (obj && obj.mensagem) {
				fraseFinal = obj.mensagem;
			} else {
				fraseFinal = JSON.stringify(obj);
			}
		} catch (e) {
			fraseFinal = mensagem; // mensagem simples
		}

		$modalTitle.text("Erro");
		$modalBody.html(`<div class=\"alert alert-danger\">${fraseFinal}</div>`);

		const modal = new bootstrap.Modal($modal[0]);
		modal.show();

		setTimeout(() => {
			modal.hide();
		}, 2000);
	}

	return {
		validarCPF,
		validarCNPJ,
		validarRG,
		validarEmail,
		validarTelefone,
		validarUF,
		validarDataNascimento,
		validarTexto,
		exibirSucesso,
		exibirErro
	};
})();