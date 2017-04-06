/**
 * RegistrosDoWSDLPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public interface RegistrosDoWSDLPortType extends java.rmi.Remote {

    /**
     * Retorno das Liquidações no período buscado.
     */
    public NetSupremaSicoobRetorno.server.Solicitacao RETORNO_CNAB400_01(NetSupremaSicoobRetorno.server.Servico servico) throws java.rmi.RemoteException;

    /**
     * Retorno das Liquidações no período e formato buscado.
     */
    public NetSupremaSicoobRetorno.server.RespostaRetorno RETORNO_FORMATO(NetSupremaSicoobRetorno.server.ServicoRetorno servico) throws java.rmi.RemoteException;
}
