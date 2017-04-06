package NetSupremaSicoobRetorno.server;

public class RegistrosDoWSDLPortTypeProxy implements NetSupremaSicoobRetorno.server.RegistrosDoWSDLPortType {
  private String _endpoint = null;
  private NetSupremaSicoobRetorno.server.RegistrosDoWSDLPortType registrosDoWSDLPortType = null;
  
  public RegistrosDoWSDLPortTypeProxy() {
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  public RegistrosDoWSDLPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRegistrosDoWSDLPortTypeProxy();
  }
  
  private void _initRegistrosDoWSDLPortTypeProxy() {
    try {
      registrosDoWSDLPortType = (new NetSupremaSicoobRetorno.server.RegistrosDoWSDLLocator()).getRegistrosDoWSDLPort();
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
  
  public NetSupremaSicoobRetorno.server.RegistrosDoWSDLPortType getRegistrosDoWSDLPortType() {
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType;
  }
  
  public NetSupremaSicoobRetorno.server.Solicitacao RETORNO_CNAB400_01(NetSupremaSicoobRetorno.server.Servico servico) throws java.rmi.RemoteException{
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType.RETORNO_CNAB400_01(servico);
  }
  
  public NetSupremaSicoobRetorno.server.RespostaRetorno RETORNO_FORMATO(NetSupremaSicoobRetorno.server.ServicoRetorno servico) throws java.rmi.RemoteException{
    if (registrosDoWSDLPortType == null)
      _initRegistrosDoWSDLPortTypeProxy();
    return registrosDoWSDLPortType.RETORNO_FORMATO(servico);
  }
  
  
}