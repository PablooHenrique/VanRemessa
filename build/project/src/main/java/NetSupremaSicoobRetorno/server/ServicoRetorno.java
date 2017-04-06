/**
 * ServicoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public class ServicoRetorno  implements java.io.Serializable {
    private NetSupremaSicoobRetorno.server.AutenticacaoRetorno autenticacao;

    private NetSupremaSicoobRetorno.server.RespostaRetorno solicitacao;

    public ServicoRetorno() {
    }

    public ServicoRetorno(
           NetSupremaSicoobRetorno.server.AutenticacaoRetorno autenticacao,
           NetSupremaSicoobRetorno.server.RespostaRetorno solicitacao) {
           this.autenticacao = autenticacao;
           this.solicitacao = solicitacao;
    }


    /**
     * Gets the autenticacao value for this ServicoRetorno.
     * 
     * @return autenticacao
     */
    public NetSupremaSicoobRetorno.server.AutenticacaoRetorno getAutenticacao() {
        return autenticacao;
    }


    /**
     * Sets the autenticacao value for this ServicoRetorno.
     * 
     * @param autenticacao
     */
    public void setAutenticacao(NetSupremaSicoobRetorno.server.AutenticacaoRetorno autenticacao) {
        this.autenticacao = autenticacao;
    }


    /**
     * Gets the solicitacao value for this ServicoRetorno.
     * 
     * @return solicitacao
     */
    public NetSupremaSicoobRetorno.server.RespostaRetorno getSolicitacao() {
        return solicitacao;
    }


    /**
     * Sets the solicitacao value for this ServicoRetorno.
     * 
     * @param solicitacao
     */
    public void setSolicitacao(NetSupremaSicoobRetorno.server.RespostaRetorno solicitacao) {
        this.solicitacao = solicitacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServicoRetorno)) return false;
        ServicoRetorno other = (ServicoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.autenticacao==null && other.getAutenticacao()==null) || 
             (this.autenticacao!=null &&
              this.autenticacao.equals(other.getAutenticacao()))) &&
            ((this.solicitacao==null && other.getSolicitacao()==null) || 
             (this.solicitacao!=null &&
              this.solicitacao.equals(other.getSolicitacao())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAutenticacao() != null) {
            _hashCode += getAutenticacao().hashCode();
        }
        if (getSolicitacao() != null) {
            _hashCode += getSolicitacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServicoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "ServicoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autenticacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Autenticacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "AutenticacaoRetorno"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Solicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "RespostaRetorno"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
