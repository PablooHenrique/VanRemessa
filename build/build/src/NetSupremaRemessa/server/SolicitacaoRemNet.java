/**
 * SolicitacaoRemNet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaRemessa.server;

public class SolicitacaoRemNet  implements java.io.Serializable {
    private java.lang.String SITUACAO;

    private java.lang.String DETALHE_ERRO;

    private java.lang.String CAPTURADO;

    private java.lang.String DATETIME;

    public SolicitacaoRemNet() {
    }

    public SolicitacaoRemNet(
           java.lang.String SITUACAO,
           java.lang.String DETALHE_ERRO,
           java.lang.String CAPTURADO,
           java.lang.String DATETIME) {
           this.SITUACAO = SITUACAO;
           this.DETALHE_ERRO = DETALHE_ERRO;
           this.CAPTURADO = CAPTURADO;
           this.DATETIME = DATETIME;
    }


    /**
     * Gets the SITUACAO value for this SolicitacaoRemNet.
     * 
     * @return SITUACAO
     */
    public java.lang.String getSITUACAO() {
        return SITUACAO;
    }


    /**
     * Sets the SITUACAO value for this SolicitacaoRemNet.
     * 
     * @param SITUACAO
     */
    public void setSITUACAO(java.lang.String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }


    /**
     * Gets the DETALHE_ERRO value for this SolicitacaoRemNet.
     * 
     * @return DETALHE_ERRO
     */
    public java.lang.String getDETALHE_ERRO() {
        return DETALHE_ERRO;
    }


    /**
     * Sets the DETALHE_ERRO value for this SolicitacaoRemNet.
     * 
     * @param DETALHE_ERRO
     */
    public void setDETALHE_ERRO(java.lang.String DETALHE_ERRO) {
        this.DETALHE_ERRO = DETALHE_ERRO;
    }


    /**
     * Gets the CAPTURADO value for this SolicitacaoRemNet.
     * 
     * @return CAPTURADO
     */
    public java.lang.String getCAPTURADO() {
        return CAPTURADO;
    }


    /**
     * Sets the CAPTURADO value for this SolicitacaoRemNet.
     * 
     * @param CAPTURADO
     */
    public void setCAPTURADO(java.lang.String CAPTURADO) {
        this.CAPTURADO = CAPTURADO;
    }


    /**
     * Gets the DATETIME value for this SolicitacaoRemNet.
     * 
     * @return DATETIME
     */
    public java.lang.String getDATETIME() {
        return DATETIME;
    }


    /**
     * Sets the DATETIME value for this SolicitacaoRemNet.
     * 
     * @param DATETIME
     */
    public void setDATETIME(java.lang.String DATETIME) {
        this.DATETIME = DATETIME;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitacaoRemNet)) return false;
        SolicitacaoRemNet other = (SolicitacaoRemNet) obj;
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
            ((this.DETALHE_ERRO==null && other.getDETALHE_ERRO()==null) || 
             (this.DETALHE_ERRO!=null &&
              this.DETALHE_ERRO.equals(other.getDETALHE_ERRO()))) &&
            ((this.CAPTURADO==null && other.getCAPTURADO()==null) || 
             (this.CAPTURADO!=null &&
              this.CAPTURADO.equals(other.getCAPTURADO()))) &&
            ((this.DATETIME==null && other.getDATETIME()==null) || 
             (this.DATETIME!=null &&
              this.DATETIME.equals(other.getDATETIME())));
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
        if (getDETALHE_ERRO() != null) {
            _hashCode += getDETALHE_ERRO().hashCode();
        }
        if (getCAPTURADO() != null) {
            _hashCode += getCAPTURADO().hashCode();
        }
        if (getDATETIME() != null) {
            _hashCode += getDATETIME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitacaoRemNet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "SolicitacaoRemNet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SITUACAO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SITUACAO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DETALHE_ERRO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DETALHE_ERRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAPTURADO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CAPTURADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATETIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATETIME"));
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
