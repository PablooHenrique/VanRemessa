/**
 * RegistrosDoWSDLPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

public interface RegistrosDoWSDLPortType extends java.rmi.Remote {

    /**
     * Consulta de processamento de remessas enviadas via Webservice.
     */
    public NetSupremaConsultaProcessamento.server.RetornoProcessamento CONSULTA_PROCESSAMENTO_REMESSA_TITULO(NetSupremaConsultaProcessamento.server.ServicoConsultaProcessamentoRemessa servico) throws java.rmi.RemoteException;
}
