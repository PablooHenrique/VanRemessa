/**
 * ServicoConsultaProcessamentoRemessa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

public class ServicoConsultaProcessamentoRemessa  implements java.io.Serializable {
    private NetSupremaConsultaProcessamento.server.Autenticacao autenticacao;

    private NetSupremaConsultaProcessamento.server.DadosConsulta dadosConsulta;

    private NetSupremaConsultaProcessamento.server.RetornoProcessamento retornoProcessamento;

    public ServicoConsultaProcessamentoRemessa() {
    }

    public ServicoConsultaProcessamentoRemessa(
           NetSupremaConsultaProcessamento.server.Autenticacao autenticacao,
           NetSupremaConsultaProcessamento.server.DadosConsulta dadosConsulta,
           NetSupremaConsultaProcessamento.server.RetornoProcessamento retornoProcessamento) {
           this.autenticacao = autenticacao;
           this.dadosConsulta = dadosConsulta;
           this.retornoProcessamento = retornoProcessamento;
    }


    /**
     * Gets the autenticacao value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @return autenticacao
     */
    public NetSupremaConsultaProcessamento.server.Autenticacao getAutenticacao() {
        return autenticacao;
    }


    /**
     * Sets the autenticacao value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @param autenticacao
     */
    public void setAutenticacao(NetSupremaConsultaProcessamento.server.Autenticacao autenticacao) {
        this.autenticacao = autenticacao;
    }


    /**
     * Gets the dadosConsulta value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @return dadosConsulta
     */
    public NetSupremaConsultaProcessamento.server.DadosConsulta getDadosConsulta() {
        return dadosConsulta;
    }


    /**
     * Sets the dadosConsulta value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @param dadosConsulta
     */
    public void setDadosConsulta(NetSupremaConsultaProcessamento.server.DadosConsulta dadosConsulta) {
        this.dadosConsulta = dadosConsulta;
    }


    /**
     * Gets the retornoProcessamento value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @return retornoProcessamento
     */
    public NetSupremaConsultaProcessamento.server.RetornoProcessamento getRetornoProcessamento() {
        return retornoProcessamento;
    }


    /**
     * Sets the retornoProcessamento value for this ServicoConsultaProcessamentoRemessa.
     * 
     * @param retornoProcessamento
     */
    public void setRetornoProcessamento(NetSupremaConsultaProcessamento.server.RetornoProcessamento retornoProcessamento) {
        this.retornoProcessamento = retornoProcessamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServicoConsultaProcessamentoRemessa)) return false;
        ServicoConsultaProcessamentoRemessa other = (ServicoConsultaProcessamentoRemessa) obj;
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
            ((this.dadosConsulta==null && other.getDadosConsulta()==null) || 
             (this.dadosConsulta!=null &&
              this.dadosConsulta.equals(other.getDadosConsulta()))) &&
            ((this.retornoProcessamento==null && other.getRetornoProcessamento()==null) || 
             (this.retornoProcessamento!=null &&
              this.retornoProcessamento.equals(other.getRetornoProcessamento())));
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
        if (getDadosConsulta() != null) {
            _hashCode += getDadosConsulta().hashCode();
        }
        if (getRetornoProcessamento() != null) {
            _hashCode += getRetornoProcessamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServicoConsultaProcessamentoRemessa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "ServicoConsultaProcessamentoRemessa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autenticacao");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Autenticacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "Autenticacao"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dadosConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DadosConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "DadosConsulta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retornoProcessamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RetornoProcessamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "RetornoProcessamento"));
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
