package br.com.igrejabatistadocordeiro.oanse.domain.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "e_id")
    private Long id;

    @Column(name = "e_rua", nullable = false, length = 200)	
	private String rua;
    
    @Column(name = "e_numero", nullable = false, length = 10)
    private String numero;
    
    @Column(name = "e_bairro", length = 100)
    private String bairro;
    
    @Column(name = "e_cidade", length = 100)
    private String cidade;
    
    @Column(name = "e_uf", length = 2)
    private String uf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, numero, rua, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(numero, other.numero) && Objects.equals(rua, other.rua)
				&& Objects.equals(uf, other.uf);
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + "]";
	}

}
