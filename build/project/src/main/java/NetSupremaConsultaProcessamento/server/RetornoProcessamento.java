/**
 * RetornoProcessamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

public class RetornoProcessamento  implements java.io.Serializable {
    private java.lang.String SITUACAO;

    private java.lang.String DETALHE_SITUACAO;

    private java.lang.String LOG_PROCESSAMENTO;

    private java.lang.String DHPROCESSAMENTO;

    public RetornoProcessamento() {
    }

    public RetornoProcessamento(
           java.lang.String SITUACAO,
           java.lang.String DETALHE_SITUACAO,
           java.lang.String LOG_PROCESSAMENTO,
           java.lang.String DHPROCESSAMENTO) {
           this.SITUACAO = SITUACAO;
           this.DETALHE_SITUACAO = DETALHE_SITUACAO;
           this.LOG_PROCESSAMENTO = LOG_PROCESSAMENTO;
           this.DHPROCESSAMENTO = DHPROCESSAMENTO;
    }


    /**
     * Gets the SITUACAO value for this RetornoProcessamento.
     * 
     * @return SITUACAO
     */
    public java.lang.String getSITUACAO() {
        return SITUACAO;
    }


    /**
     * Sets the SITUACAO value for this RetornoProcessamento.
     * 
     * @param SITUACAO
     */
    public void setSITUACAO(java.lang.String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }


    /**
     * Gets the DETALHE_SITUACAO value for this RetornoProcessamento.
     * 
     * @return DETALHE_SITUACAO
     */
    public java.lang.String getDETALHE_SITUACAO() {
        return DETALHE_SITUACAO;
    }


    /**
     * Sets the DETALHE_SITUACAO value for this RetornoProcessamento.
     * 
     * @param DETALHE_SITUACAO
     */
    public void setDETALHE_SITUACAO(java.lang.String DETALHE_SITUACAO) {
        this.DETALHE_SITUACAO = DETALHE_SITUACAO;
    }


    /**
     * Gets the LOG_PROCESSAMENTO value for this RetornoProcessamento.
     * 
     * @return LOG_PROCESSAMENTO
     */
    public java.lang.String getLOG_PROCESSAMENTO() {
        return LOG_PROCESSAMENTO;
    }


    /**
     * Sets the LOG_PROCESSAMENTO value for this RetornoProcessamento.
     * 
     * @param LOG_PROCESSAMENTO
     */
    public void setLOG_PROCESSAMENTO(java.lang.String LOG_PROCESSAMENTO) {
        this.LOG_PROCESSAMENTO = LOG_PROCESSAMENTO;
    }


    /**
     * Gets the DHPROCESSAMENTO value for this RetornoProcessamento.
     * 
     * @return DHPROCESSAMENTO
     */
    public java.lang.String getDHPROCESSAMENTO() {
        return DHPROCESSAMENTO;
    }


    /**
     * Sets the DHPROCESSAMENTO value for this RetornoProcessamento.
     * 
     * @param DHPROCESSAMENTO
     */
    public void setDHPROCESSAMENTO(java.lang.String DHPROCESSAMENTO) {
        this.DHPROCESSAMENTO = DHPROCESSAMENTO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetornoProcessamento)) return false;
        RetornoProcessamento other = (RetornoProcessamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.SITUACAO==null && other.getSITUACAO()==null) || 
             (this.SITUACAO!=null &&
              this.SITUACAO.equals(other.getSITUACAO()))) &&
            ((this.DETALHE_SITUACAO==null && other.getDETALHE_SITUACAO()==null) || 
             (this.DETALHE_SITUACAO!=null &&
              this.DETALHE_SITUACAO.equals(other.getDETALHE_SITUACAO()))) &&
            ((this.LOG_PROCESSAMENTO==null && other.getLOG_PROCESSAMENTO()==null) || 
             (this.LOG_PROCESSAMENTO!=null &&
              this.LOG_PROCESSAMENTO.equals(other.getLOG_PROCESSAMENTO()))) &&
            ((this.DHPROCESSAMENTO==null && other.getDHPROCESSAMENTO()==null) || 
             (this.DHPROCESSAMENTO!=null &&
              this.DHPROCESSAMENTO.equals(other.getDHPROCESSAMENTO())));
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
        if (getSITUACAO() != null) {
            _hashCode += getSITUACAO().hashCode();
        }
        if (getDETALHE_SITUACAO() != null) {
            _hashCode += getDETALHE_SITUACAO().hashCode();
        }
        if (getLOG_PROCESSAMENTO() != null) {
            _hashCode += getLOG_PROCESSAMENTO().hashCode();
        }
        if (getDHPROCESSAMENTO() != null) {
            _hashCode += getDHPROCESSAMENTO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetornoProcessamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "RetornoProcessamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SITUACAO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SITUACAO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DETALHE_SITUACAO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DETALHE_SITUACAO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOG_PROCESSAMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOG_PROCESSAMENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DHPROCESSAMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DHPROCESSAMENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
