package br.com.netsuprema.dominio.remessa;

public enum StatusProcessamento {
		AGURDANDO_PROCESSAMENTO_PROCESSAMENTO(01, "")
	  , IMPORTACAO_REALIZADA_COM_SUCESSO(02, "")
	  , ERRO_03(03, "")
	  , USUARIO_OU_SENHA_NAO_DEFINIDO(04, "USUARIO OU SENHA N�O DEFINIDO")
	  , CODIGO_CEDENTE_NAO_DEFINIDO(05, "CODIGO CEDENTE NAO DEFINIDO OU DIFERENTE DO FORMATO NUMERICO")
	  , CONTA_CORRENTE_NAO_DEFINIDO(06, "CONTA CORRENTE NAO DEFINIDO OU DIFERENTE DO FORMATO NUMERICO")
	  , USUARIO_NAO_ENCONTRADO(07, "USUARIO NAO ENCONTRADO OU SENHA NAO CONFERE COM USUARIO")
	  , USURIO_SEM_DIREITO(8, "USURIO_SEM_DIREITO")
	  , CODIGO_AGENDAMENTO_NAO_DEFINIDO(9, "CODIGO_LOTE_AGENDAMENTO N�O DEFINIDO OU DIFERENTE DO FORMATO NUMERICO")
	  , AGENDAMENTO_NAO_ENCONTRADO(10, "AGENDAMENTO DE REMESSA N�O ENCONTRADO OU N�O RELACIONADO AOS CEDENTES QUE TEM DIREITO");
	
	
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