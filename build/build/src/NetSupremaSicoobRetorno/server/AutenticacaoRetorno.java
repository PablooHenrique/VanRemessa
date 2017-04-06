/**
 * AutenticacaoRetorno.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public class AutenticacaoRetorno  implements java.io.Serializable {
    private java.lang.String USUARIO;

    private java.lang.String SENHA_MD5;

    private java.lang.String CODIGO_CEDENTE;

    private java.lang.String CONTA_CORRENTE;

    private java.lang.String DTRETORNO;

    private java.lang.String FORMATO;

    public AutenticacaoRetorno() {
    }

    public AutenticacaoRetorno(
           java.lang.String USUARIO,
           java.lang.String SENHA_MD5,
           java.lang.String CODIGO_CEDENTE,
           java.lang.String CONTA_CORRENTE,
           java.lang.String DTRETORNO,
           java.lang.String FORMATO) {
           this.USUARIO = USUARIO;
           this.SENHA_MD5 = SENHA_MD5;
           this.CODIGO_CEDENTE = CODIGO_CEDENTE;
           this.CONTA_CORRENTE = CONTA_CORRENTE;
           this.DTRETORNO = DTRETORNO;
           this.FORMATO = FORMATO;
    }


    /**
     * Gets the USUARIO value for this AutenticacaoRetorno.
     * 
     * @return USUARIO
     */
    public java.lang.String getUSUARIO() {
        return USUARIO;
    }


    /**
     * Sets the USUARIO value for this AutenticacaoRetorno.
     * 
     * @param USUARIO
     */
    public void setUSUARIO(java.lang.String USUARIO) {
        this.USUARIO = USUARIO;
    }


    /**
     * Gets the SENHA_MD5 value for this AutenticacaoRetorno.
     * 
     * @return SENHA_MD5
     */
    public java.lang.String getSENHA_MD5() {
        return SENHA_MD5;
    }


    /**
     * Sets the SENHA_MD5 value for this AutenticacaoRetorno.
     * 
     * @param SENHA_MD5
     */
    public void setSENHA_MD5(java.lang.String SENHA_MD5) {
        this.SENHA_MD5 = SENHA_MD5;
    }


    /**
     * Gets the CODIGO_CEDENTE value for this AutenticacaoRetorno.
     * 
     * @return CODIGO_CEDENTE
     */
    public java.lang.String getCODIGO_CEDENTE() {
        return CODIGO_CEDENTE;
    }


    /**
     * Sets the CODIGO_CEDENTE value for this AutenticacaoRetorno.
     * 
     * @param CODIGO_CEDENTE
     */
    public void setCODIGO_CEDENTE(java.lang.String CODIGO_CEDENTE) {
        this.CODIGO_CEDENTE = CODIGO_CEDENTE;
    }


    /**
     * Gets the CONTA_CORRENTE value for this AutenticacaoRetorno.
     * 
     * @return CONTA_CORRENTE
     */
    public java.lang.String getCONTA_CORRENTE() {
        return CONTA_CORRENTE;
    }


    /**
     * Sets the CONTA_CORRENTE value for this AutenticacaoRetorno.
     * 
     * @param CONTA_CORRENTE
     */
    public void setCONTA_CORRENTE(java.lang.String CONTA_CORRENTE) {
        this.CONTA_CORRENTE = CONTA_CORRENTE;
    }


    /**
     * Gets the DTRETORNO value for this AutenticacaoRetorno.
     * 
     * @return DTRETORNO
     */
    public java.lang.String getDTRETORNO() {
        return DTRETORNO;
    }


    /**
     * Sets the DTRETORNO value for this AutenticacaoRetorno.
     * 
     * @param DTRETORNO
     */
    public void setDTRETORNO(java.lang.String DTRETORNO) {
        this.DTRETORNO = DTRETORNO;
    }


    /**
     * Gets the FORMATO value for this AutenticacaoRetorno.
     * 
     * @return FORMATO
     */
    public java.lang.String getFORMATO() {
        return FORMATO;
    }


    /**
     * Sets the FORMATO value for this AutenticacaoRetorno.
     * 
     * @param FORMATO
     */
    public void setFORMATO(java.lang.String FORMATO) {
        this.FORMATO = FORMATO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutenticacaoRetorno)) return false;
        AutenticacaoRetorno other = (AutenticacaoRetorno) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.USUARIO==null && other.getUSUARIO()==null) || 
             (this.USUARIO!=null &&
              this.USUARIO.equals(other.getUSUARIO()))) &&
            ((this.SENHA_MD5==null && other.getSENHA_MD5()==null) || 
             (this.SENHA_MD5!=null &&
              this.SENHA_MD5.equals(other.getSENHA_MD5()))) &&
            ((this.CODIGO_CEDENTE==null && other.getCODIGO_CEDENTE()==null) || 
             (this.CODIGO_CEDENTE!=null &&
              this.CODIGO_CEDENTE.equals(other.getCODIGO_CEDENTE()))) &&
            ((this.CONTA_CORRENTE==null && other.getCONTA_CORRENTE()==null) || 
             (this.CONTA_CORRENTE!=null &&
              this.CONTA_CORRENTE.equals(other.getCONTA_CORRENTE()))) &&
            ((this.DTRETORNO==null && other.getDTRETORNO()==null) || 
             (this.DTRETORNO!=null &&
              this.DTRETORNO.equals(other.getDTRETORNO()))) &&
            ((this.FORMATO==null && other.getFORMATO()==null) || 
             (this.FORMATO!=null &&
              this.FORMATO.equals(other.getFORMATO())));
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
        if (getUSUARIO() != null) {
            _hashCode += getUSUARIO().hashCode();
        }
        if (getSENHA_MD5() != null) {
            _hashCode += getSENHA_MD5().hashCode();
        }
        if (getCODIGO_CEDENTE() != null) {
            _hashCode += getCODIGO_CEDENTE().hashCode();
        }
        if (getCONTA_CORRENTE() != null) {
            _hashCode += getCONTA_CORRENTE().hashCode();
        }
        if (getDTRETORNO() != null) {
            _hashCode += getDTRETORNO().hashCode();
        }
        if (getFORMATO() != null) {
            _hashCode += getFORMATO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutenticacaoRetorno.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "AutenticacaoRetorno"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USUARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SENHA_MD5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SENHA_MD5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_CEDENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_CEDENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTA_CORRENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTA_CORRENTE"));
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
        elemField.setFieldName("FORMATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FORMATO"));
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
