package br.com.netsuprema.dominio.parametros;

public enum FormatoRetornoLiquidacao {
	  PADRAO_SISTEMA(1, "Padr�o Sistema")
	, PADRAO_CNAB_400(2, "Padr�o Cnab400(Dispon�vel por 60 dias)")
	, PADRAO_CNAB_240(3, "Padr�o Cnab240(Dispon�vel por 60 dias)")
	, CNAB_400_NETSUPREMA(4, "Padr�o Cnab400 (Montado pela Net Suprema)")
	, CNAB_400_CONDOMINIO_21(5, "Padr�o Cnab400(Montado para condominio 21 - Alt. POS 28 � 62)");
	
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
