/**
 * RegistrosDoWSDLLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

import br.com.netsuprema.service.parametros.ConfiguracoesGeraisProjetoService;

public class RegistrosDoWSDLLocator extends org.apache.axis.client.Service implements NetSupremaConsultaProcessamento.server.RegistrosDoWSDL {

    public RegistrosDoWSDLLocator() {
    }


    public RegistrosDoWSDLLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RegistrosDoWSDLLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

//    Use to get a proxy class for RegistrosDoWSDLPort
//    private java.lang.String RegistrosDoWSDLPort_address = "http://sig.netsuprema.com.br/col_cobrancateste/webservice_consulta_processamento_remessa.php";
//    private java.lang.String RegistrosDoWSDLPort_address = "http://192.168.7.205/joao/aplication/webservice_consulta_processamento_remessa.php";
    
    private java.lang.String RegistrosDoWSDLPort_address = new ConfiguracoesGeraisProjetoService().carregarLinkCooperativaEscolhida() + "webservice_consulta_processamento_remessa.php";

    public java.lang.String getRegistrosDoWSDLPortAddress() {
        return RegistrosDoWSDLPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RegistrosDoWSDLPortWSDDServiceName = "Registros do WSDLPort";

    public java.lang.String getRegistrosDoWSDLPortWSDDServiceName() {
        return RegistrosDoWSDLPortWSDDServiceName;
    }

    public void setRegistrosDoWSDLPortWSDDServiceName(java.lang.String name) {
        RegistrosDoWSDLPortWSDDServiceName = name;
    }

    public NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType getRegistrosDoWSDLPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RegistrosDoWSDLPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRegistrosDoWSDLPort(endpoint);
    }

    public NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType getRegistrosDoWSDLPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            NetSupremaConsultaProcessamento.server.RegistrosDoWSDLBindingStub _stub = new NetSupremaConsultaProcessamento.server.RegistrosDoWSDLBindingStub(portAddress, this);
            _stub.setPortName(getRegistrosDoWSDLPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRegistrosDoWSDLPortEndpointAddress(java.lang.String address) {
        RegistrosDoWSDLPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                NetSupremaConsultaProcessamento.server.RegistrosDoWSDLBindingStub _stub = new NetSupremaConsultaProcessamento.server.RegistrosDoWSDLBindingStub(new java.net.URL(RegistrosDoWSDLPort_address), this);
                _stub.setPortName(getRegistrosDoWSDLPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Registros do WSDLPort".equals(inputPortName)) {
            return getRegistrosDoWSDLPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "Registros do WSDL");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "Registros do WSDLPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RegistrosDoWSDLPort".equals(portName)) {
            setRegistrosDoWSDLPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
