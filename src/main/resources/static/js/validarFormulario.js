function validarFormulario() {
  let valido = true;

  const camposObrigatorios = [
    "#descricao",
    "#tipo",
    "#dataNascimento",
    "#telefone1",
    "#rua",
    "#bairro",
    "#cidade",
    "#uf"
  ];

  camposObrigatorios.forEach(function (campo) {
    const valor = $(campo).val().trim();
    if (!valor) {
      $(campo).addClass("is-invalid");
      valido = false;
    } else {
      $(campo).removeClass("is-invalid");
    }
  });

  // Validação de CPF
  const tipo = $("#tipo").val();
  const cpf = $("#cpf").val().replace(/\D/g, "");
  const cnpj = $("#cnpj").val().replace(/\D/g, "");
  const email = $("#email").val().trim();
  const dataNasc = $("#dataNascimento").val();
  const rg = $("#rg").val().trim();
  const uf = $("#uf").val().trim();

  if (tipo === "FISICA" && cpf && !validarCPF(cpf)) {
    $("#cpf").addClass("is-invalid");
    valido = false;
  }

  if (tipo === "JURIDICA" && cnpj && !validarCNPJ(cnpj)) {
    $("#cnpj").addClass("is-invalid");
    valido = false;
  }

  if (email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    $("#email").addClass("is-invalid");
    valido = false;
  }

  if (dataNasc && new Date(dataNasc) > new Date()) {
    $("#dataNascimento").addClass("is-invalid");
    valido = false;
  }
  
  if (rg && !/^\d{1,2}\.\d{3}\.\d{3}-\d{1}$/.test(rg)) {
	$("#rg").addClass("is-invalid");
    valido = false;
  }
  
  if (uf && !/^[A-Z]{2}$/.test(uf)) {
	$("#uf").addClass("is-invalid");
    valido = false;
  }

  return valido;
}


// === Funções de validação ===
function validarCPF(cpf) {
  cpf = cpf.replace(/[^\d]+/g, '');
  if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) return false;

  let soma = 0;
  for (let i = 0; i < 9; i++) soma += parseInt(cpf.charAt(i)) * (10 - i);
  let resto = (soma * 10) % 11;
  if (resto === 10 || resto === 11) resto = 0;
  if (resto !== parseInt(cpf.charAt(9))) return false;

  soma = 0;
  for (let i = 0; i < 10; i++) soma += parseInt(cpf.charAt(i)) * (11 - i);
  resto = (soma * 10) % 11;
  if (resto === 10 || resto === 11) resto = 0;
  return resto === parseInt(cpf.charAt(10));
}

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

  let resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
  if (resultado !== parseInt(digitos.charAt(0))) return false;

  tamanho += 1;
  numeros = cnpj.substring(0, tamanho);
  soma = 0;
  pos = tamanho - 7;

  for (let i = tamanho; i >= 1; i--) {
    soma += parseInt(numeros.charAt(tamanho - i)) * pos--;
    if (pos < 2) pos = 9;
  }

  resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
  return resultado === parseInt(digitos.charAt(1));
}