/**
 * Autenticacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaSicoobRetorno.server;

public class Autenticacao  implements java.io.Serializable {
    private java.lang.String USUARIO;

    private java.lang.String CODIGO_CEDENTE;

    private java.lang.String CONTA_CORRENTE;

    private java.lang.String DTRETORNO;

    public Autenticacao() {
    }

    public Autenticacao(
           java.lang.String USUARIO,
           java.lang.String CODIGO_CEDENTE,
           java.lang.String CONTA_CORRENTE,
           java.lang.String DTRETORNO) {
           this.USUARIO = USUARIO;
           this.CODIGO_CEDENTE = CODIGO_CEDENTE;
           this.CONTA_CORRENTE = CONTA_CORRENTE;
           this.DTRETORNO = DTRETORNO;
    }


    /**
     * Gets the USUARIO value for this Autenticacao.
     * 
     * @return USUARIO
     */
    public java.lang.String getUSUARIO() {
        return USUARIO;
    }


    /**
     * Sets the USUARIO value for this Autenticacao.
     * 
     * @param USUARIO
     */
    public void setUSUARIO(java.lang.String USUARIO) {
        this.USUARIO = USUARIO;
    }


    /**
     * Gets the CODIGO_CEDENTE value for this Autenticacao.
     * 
     * @return CODIGO_CEDENTE
     */
    public java.lang.String getCODIGO_CEDENTE() {
        return CODIGO_CEDENTE;
    }


    /**
     * Sets the CODIGO_CEDENTE value for this Autenticacao.
     * 
     * @param CODIGO_CEDENTE
     */
    public void setCODIGO_CEDENTE(java.lang.String CODIGO_CEDENTE) {
        this.CODIGO_CEDENTE = CODIGO_CEDENTE;
    }


    /**
     * Gets the CONTA_CORRENTE value for this Autenticacao.
     * 
     * @return CONTA_CORRENTE
     */
    public java.lang.String getCONTA_CORRENTE() {
        return CONTA_CORRENTE;
    }


    /**
     * Sets the CONTA_CORRENTE value for this Autenticacao.
     * 
     * @param CONTA_CORRENTE
     */
    public void setCONTA_CORRENTE(java.lang.String CONTA_CORRENTE) {
        this.CONTA_CORRENTE = CONTA_CORRENTE;
    }


    /**
     * Gets the DTRETORNO value for this Autenticacao.
     * 
     * @return DTRETORNO
     */
    public java.lang.String getDTRETORNO() {
        return DTRETORNO;
    }


    /**
     * Sets the DTRETORNO value for this Autenticacao.
     * 
     * @param DTRETORNO
     */
    public void setDTRETORNO(java.lang.String DTRETORNO) {
        this.DTRETORNO = DTRETORNO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Autenticacao)) return false;
        Autenticacao other = (Autenticacao) obj;
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
            ((this.CODIGO_CEDENTE==null && other.getCODIGO_CEDENTE()==null) || 
             (this.CODIGO_CEDENTE!=null &&
              this.CODIGO_CEDENTE.equals(other.getCODIGO_CEDENTE()))) &&
            ((this.CONTA_CORRENTE==null && other.getCONTA_CORRENTE()==null) || 
             (this.CONTA_CORRENTE!=null &&
              this.CONTA_CORRENTE.equals(other.getCONTA_CORRENTE()))) &&
            ((this.DTRETORNO==null && other.getDTRETORNO()==null) || 
             (this.DTRETORNO!=null &&
              this.DTRETORNO.equals(other.getDTRETORNO())));
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
        if (getCODIGO_CEDENTE() != null) {
            _hashCode += getCODIGO_CEDENTE().hashCode();
        }
        if (getCONTA_CORRENTE() != null) {
            _hashCode += getCONTA_CORRENTE().hashCode();
        }
        if (getDTRETORNO() != null) {
            _hashCode += getDTRETORNO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Autenticacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaSicoobRetorno", "Autenticacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "USUARIO"));
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
