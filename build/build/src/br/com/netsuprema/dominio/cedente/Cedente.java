package br.com.netsuprema.dominio.cedente;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.Session;
import org.hibernate.annotations.ManyToAny;

import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.repository.CedenteRepository;


@Entity(name="cedente")
public class Cedente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private int codigo;
	private int digitoVerificador;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="cedente_contas", joinColumns={@JoinColumn(name="cedente_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="conta_id", referencedColumnName="id")})
	private List<Conta> contas;
	private String diretorioPadrao;
	
	public boolean cedenteExiste(Session session) throws IllegalArgumentException  {
		if (getId() > 0) {
			return verificaSeCedenteExistePorId(session);
		}else if(getCodigo() > 0){
			return verificaSeCedenteExistePorCodigo(session);
		}else{
			throw new IllegalArgumentException("Objeto cedente não possui as informações necessarias para realizar a verificação se existe.");
		}
	}
	
	private boolean verificaSeCedenteExistePorCodigo(Session session) {
		Cedente cedente = new CedenteRepository(session).listarPorCodigo(getCodigo());
		return cedente != null;
	}

	private boolean verificaSeCedenteExistePorId(Session session) {
		Cedente cedente = new CedenteRepository(session).listarPorId(getId());
		return cedente != null;
	}

	/*Getter ands setters*/
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(int digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public String getDiretorioPadrao() {
		return diretorioPadrao;
	}

	public void setDiretorioPadrao(String diretorioPadrao) {
		this.diretorioPadrao = diretorioPadrao;
	}
}
