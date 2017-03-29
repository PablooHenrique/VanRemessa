package br.com.netsuprema.dominio.remessa;

public enum StatusProcessamento {
		AGURDANDO_PROCESSAMENTO_PROCESSAMENTO(01, "")
	  , IMPORTACAO_REALIZADA_COM_SUCESSO(02, "")
	  , ERRO_03(03, "")
	  , ERRO_04(04, "")
	  , ERRO_05(05, "")
	  , ERRO_06(06, "")
	  , ERRO_07(07, "")
	  , ERRO_08(8, "")
	  , ERRO_09(9, "")
	  , ERRO_10(10, "");
	
	
	private int codigo;
	private String descricao;
	
	StatusProcessamento(int codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
}
