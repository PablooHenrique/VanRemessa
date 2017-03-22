package br.com.netsuprema.dominio.enuns;

public enum FormatoRemessa {
	  REMESSA_NET_SUPREMA_TAMANHO_FIXO(1,"Remessa Netsuprema(Tamanho fixo)")
	, REMESSA_NET_SUPREMA_DELIMITADO_POR_TAB(2,"Remessa Netsuprema(Delimitado por tab)")
	, REMESSA_ATUALIZACAO_NET_SUPREMA(3,"Remessa Atualização Netsuprema")
	, REMESSA_CNAB_400(4,"Remessa CNAB400")
	, REMESSA_CNAB_240(5,"Remessa CNAB240"); 
	
	private int codigo;
	private String descricao;
	
	FormatoRemessa(int codigo, String descricao){
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
