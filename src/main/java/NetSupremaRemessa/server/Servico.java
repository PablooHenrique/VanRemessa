/**
 * Servico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaRemessa.server;

public class Servico  implements java.io.Serializable {
    private NetSupremaRemessa.server.Autenticacao autenticacao;

    private NetSupremaRemessa.server.Cedente cedente;

    private NetSupremaRemessa.server.Dados dados;

    private NetSupremaRemessa.server.Solicitacao solicitacao;

    public Servico() {
    }

    public Servico(
           NetSupremaRemessa.server.Autenticacao autenticacao,
           NetSupremaRemessa.server.Cedente cedente,
           NetSupremaRemessa.server.Dados dados,
           NetSupremaRemessa.server.Solicitacao solicitacao) {
           this.autenticacao = autenticacao;
           this.cedente = cedente;
           this.dados = dados;
           this.solicitacao = solicitacao;
    }


    /**
     * Gets the autenticacao value for this Servico.
     * 
     * @return autenticacao
     */
    public NetSupremaRemessa.server.Autenticacao getAutenticacao() {
        return autenticacao;
    }


    /**
     * Sets the autenticacao value for this Servico.
     * 
     * @param autenticacao
     */
    public void setAutenticacao(NetSupremaRemessa.server.Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }


    /**
     * Gets the cedente value for this Servico.
     * 
     * @return cedente
     */
    public NetSupremaRemessa.server.Cedente getCedente() {
        return cedente;
    }


    /**
     * Sets the cedente value for this Servico.
     * 
     * @param cedente
     */
    public void setCedente(NetSupremaRemessa.server.Cedente cedente) {
        this.cedente = cedente;
    }


    /**
     * Gets the dados value for this Servico.
     * 
     * @return dados
     */
    public NetSupremaRemessa.server.Dados getDados() {
        return dados;
    }


    /**
     * Sets the dados value for this Servico.
     * 
     * @param dados
     */
    public void setDados(NetSupremaRemessa.server.Dados dados) {
        this.dados = dados;
    }


    /**
     * Gets the solicitacao value for this Servico.
     * 
     * @return solicitacao
     */
    public NetSupremaRemessa.server.Solicitacao getSolicitacao() {
        return solicitacao;
    }


    /**
     * Sets the solicitacao value for this Servico.
     * 
     * @param solicitacao
     */
    public void setSolicitacao(NetSupremaRemessa.server.Solicitacao solicitacao) {
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
            ((this.cedente==null && other.getCedente()==null) || 
             (this.cedente!=null &&
              this.cedente.equals(other.getCedente()))) &&
            ((this.dados==null && other.getDados()==null) || 
             (this.dados!=null &&
              this.dados.equals(other.getDados()))) &&
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
        if (getCedente() != null) {
            _hashCode += getCedente().hashCode();
        }
        if (getDados() != null) {
            _hashCode += getDados().hashCode();
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
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Servico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autenticacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Autenticacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Autenticacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Cedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Cedente"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Dados"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Dados"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Solicitacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Solicitacao"));
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
