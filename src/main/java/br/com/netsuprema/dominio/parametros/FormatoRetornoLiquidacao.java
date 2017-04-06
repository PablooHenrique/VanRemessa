package br.com.netsuprema.dominio.parametros;

public enum FormatoRetornoLiquidacao {
	  PADRAO_SISTEMA(1, "Padrão Sistema")
	, PADRAO_CNAB_400(2, "Padrão Cnab400(Disponível por 60 dias)")
	, PADRAO_CNAB_240(3, "Padrão Cnab240(Disponível por 60 dias)")
	, CNAB_400_NETSUPREMA(4, "Padrão Cnab400 (Montado pela Net Suprema)")
	, CNAB_400_CONDOMINIO_21(5, "Padrão Cnab400(Montado para condominio 21 - Alt. POS 28 à 62)");
	
	private int codigo;
	private String descricao;
	
	private FormatoRetornoLiquidacao(int codigo, String descricao) {
		this.codigo 	= codigo;
		this.descricao 	= descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
