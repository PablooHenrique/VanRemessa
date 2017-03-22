package NetSupremaRemessa.server;

public class RegistrosDoWSDLPortTypeProxy implements NetSupremaRemessa.server.RegistrosDoWSDLPortType {
  private String _endpoint = null;
  private NetSupremaRemessa.server.RegistrosDoWSDLPortType registrosDoWSDLPortType = null;
  
  public RegistrosDoWSDLPortTypeProxy() {
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  public RegistrosDoWSDLPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  private void _initRegistrosDoWSDLPortTypeProxy() {
    try {
      registrosDoWSDLPortType = (new NetSupremaRemessa.server.RegistrosDoWSDLLocator()).getRegistrosDoWSDLPort();
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
  
  public NetSupremaRemessa.server.RegistrosDoWSDLPortType getRegistrosDoWSDLPortType() {
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType;
  }
  
  public NetSupremaRemessa.server.SolicitacaoRemNet REMESSA_TITULO_FORMATO_NETSUPREMA(NetSupremaRemessa.server.ServicoRemNet servico) throws java.rmi.RemoteException{
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType.REMESSA_TITULO_FORMATO_NETSUPREMA(servico);
  }
  
  public NetSupremaRemessa.server.Solicitacao REMESSA_TITULO(NetSupremaRemessa.server.Servico servico) throws java.rmi.RemoteException{
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType.REMESSA_TITULO(servico);
  }
  
  
}