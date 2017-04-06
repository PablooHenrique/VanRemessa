/**
 * Servico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public class Servico  implements java.io.Serializable {
    private NetSupremaSicoobRetorno.server.Autenticacao autenticacao;

    private NetSupremaSicoobRetorno.server.Solicitacao solicitacao;

    public Servico() {
    }

    public Servico(
           NetSupremaSicoobRetorno.server.Autenticacao autenticacao,
           NetSupremaSicoobRetorno.server.Solicitacao solicitacao) {
           this.autenticacao = autenticacao;
           this.solicitacao = solicitacao;
    }


    /**
     * Gets the autenticacao value for this Servico.
     * 
     * @return autenticacao
     */
    public NetSupremaSicoobRetorno.server.Autenticacao getAutenticacao() {
        return autenticacao;
    }


    /**
     * Sets the autenticacao value for this Servico.
     * 
     * @param autenticacao
     */
    public void setAutenticacao(NetSupremaSicoobRetorno.server.Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }


    /**
     * Gets the solicitacao value for this Servico.
     * 
     * @return solicitacao
     */
    public NetSupremaSicoobRetorno.server.Solicitacao getSolicitacao() {
        return solicitacao;
    }


    /**
     * Sets the solicitacao value for this Servico.
     * 
     * @param solicitacao
     */
    public void setSolicitacao(NetSupremaSicoobRetorno.server.Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Servico)) return false;
        Servico other = (Servico) obj;
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
        new org.apache.axis.description.TypeDesc(Servico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "Servico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autenticacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Autenticacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "Autenticacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Solicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "Solicitacao"));
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
