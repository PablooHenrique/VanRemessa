/**
 * RegistrosDoWSDLPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaRemessa.server;

public interface RegistrosDoWSDLPortType extends java.rmi.Remote {

    /**
     * Remessa de títulos a serem importados no SIG Cobrança.
     */
    public NetSupremaRemessa.server.SolicitacaoRemNet REMESSA_TITULO_FORMATO_NETSUPREMA(NetSupremaRemessa.server.ServicoRemNet servico) throws java.rmi.RemoteException;

    /**
     * Remessa a serem importados no SIG Cobrança.
     */
    public NetSupremaRemessa.server.Solicitacao REMESSA_TITULO(NetSupremaRemessa.server.Servico servico) throws java.rmi.RemoteException;
}
