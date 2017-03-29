package NetSupremaConsultaProcessamento.server;

public class RegistrosDoWSDLPortTypeProxy implements NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType {
  private String _endpoint = null;
  private NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType registrosDoWSDLPortType = null;
  
  public RegistrosDoWSDLPortTypeProxy() {
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  public RegistrosDoWSDLPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  private void _initRegistrosDoWSDLPortTypeProxy() {
    try {
      registrosDoWSDLPortType = (new NetSupremaConsultaProcessamento.server.RegistrosDoWSDLLocator()).getRegistrosDoWSDLPort();
      if (registrosDoWSDLPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)registrosDoWSDLPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)registrosDoWSDLPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (registrosDoWSDLPortType != null)
      ((javax.xml.rpc.Stub)registrosDoWSDLPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public NetSupremaConsultaProcessamento.server.RegistrosDoWSDLPortType getRegistrosDoWSDLPortType() {
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType;
  }
  
  public NetSupremaConsultaProcessamento.server.RetornoProcessamento CONSULTA_PROCESSAMENTO_REMESSA_TITULO(NetSupremaConsultaProcessamento.server.ServicoConsultaProcessamentoRemessa servico) throws java.rmi.RemoteException{
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType.CONSULTA_PROCESSAMENTO_REMESSA_TITULO(servico);
  }
  
  
}