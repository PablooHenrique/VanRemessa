/**
 * RespostaRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public class RespostaRetorno  implements java.io.Serializable {
    private java.lang.String SITUACAO;

    private java.lang.String DETALHE_SITUACAO;

    private java.lang.String DTRETORNO;

    private java.lang.String DATETIME;

    private java.lang.String RETORNO_ARQUIVO;

    public RespostaRetorno() {
    }

    public RespostaRetorno(
           java.lang.String SITUACAO,
           java.lang.String DETALHE_SITUACAO,
           java.lang.String DTRETORNO,
           java.lang.String DATETIME,
           java.lang.String RETORNO_ARQUIVO) {
           this.SITUACAO = SITUACAO;
           this.DETALHE_SITUACAO = DETALHE_SITUACAO;
           this.DTRETORNO = DTRETORNO;
           this.DATETIME = DATETIME;
           this.RETORNO_ARQUIVO = RETORNO_ARQUIVO;
    }


    /**
     * Gets the SITUACAO value for this RespostaRetorno.
     * 
     * @return SITUACAO
     */
    public java.lang.String getSITUACAO() {
        return SITUACAO;
    }


    /**
     * Sets the SITUACAO value for this RespostaRetorno.
     * 
     * @param SITUACAO
     */
    public void setSITUACAO(java.lang.String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }


    /**
     * Gets the DETALHE_SITUACAO value for this RespostaRetorno.
     * 
     * @return DETALHE_SITUACAO
     */
    public java.lang.String getDETALHE_SITUACAO() {
        return DETALHE_SITUACAO;
    }


    /**
     * Sets the DETALHE_SITUACAO value for this RespostaRetorno.
     * 
     * @param DETALHE_SITUACAO
     */
    public void setDETALHE_SITUACAO(java.lang.String DETALHE_SITUACAO) {
        this.DETALHE_SITUACAO = DETALHE_SITUACAO;
    }


    /**
     * Gets the DTRETORNO value for this RespostaRetorno.
     * 
     * @return DTRETORNO
     */
    public java.lang.String getDTRETORNO() {
        return DTRETORNO;
    }


    /**
     * Sets the DTRETORNO value for this RespostaRetorno.
     * 
     * @param DTRETORNO
     */
    public void setDTRETORNO(java.lang.String DTRETORNO) {
        this.DTRETORNO = DTRETORNO;
    }


    /**
     * Gets the DATETIME value for this RespostaRetorno.
     * 
     * @return DATETIME
     */
    public java.lang.String getDATETIME() {
        return DATETIME;
    }


    /**
     * Sets the DATETIME value for this RespostaRetorno.
     * 
     * @param DATETIME
     */
    public void setDATETIME(java.lang.String DATETIME) {
        this.DATETIME = DATETIME;
    }


    /**
     * Gets the RETORNO_ARQUIVO value for this RespostaRetorno.
     * 
     * @return RETORNO_ARQUIVO
     */
    public java.lang.String getRETORNO_ARQUIVO() {
        return RETORNO_ARQUIVO;
    }


    /**
     * Sets the RETORNO_ARQUIVO value for this RespostaRetorno.
     * 
     * @param RETORNO_ARQUIVO
     */
    public void setRETORNO_ARQUIVO(java.lang.String RETORNO_ARQUIVO) {
        this.RETORNO_ARQUIVO = RETORNO_ARQUIVO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespostaRetorno)) return false;
        RespostaRetorno other = (RespostaRetorno) obj;
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
            ((this.DTRETORNO==null && other.getDTRETORNO()==null) || 
             (this.DTRETORNO!=null &&
              this.DTRETORNO.equals(other.getDTRETORNO()))) &&
            ((this.DATETIME==null && other.getDATETIME()==null) || 
             (this.DATETIME!=null &&
              this.DATETIME.equals(other.getDATETIME()))) &&
            ((this.RETORNO_ARQUIVO==null && other.getRETORNO_ARQUIVO()==null) || 
             (this.RETORNO_ARQUIVO!=null &&
              this.RETORNO_ARQUIVO.equals(other.getRETORNO_ARQUIVO())));
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
        if (getDTRETORNO() != null) {
            _hashCode += getDTRETORNO().hashCode();
        }
        if (getDATETIME() != null) {
            _hashCode += getDATETIME().hashCode();
        }
        if (getRETORNO_ARQUIVO() != null) {
            _hashCode += getRETORNO_ARQUIVO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespostaRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "RespostaRetorno"));
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
        elemField.setFieldName("DTRETORNO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DTRETORNO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATETIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATETIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RETORNO_ARQUIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RETORNO_ARQUIVO"));
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
