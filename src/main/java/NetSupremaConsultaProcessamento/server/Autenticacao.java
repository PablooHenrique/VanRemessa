/**
 * Autenticacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

public class Autenticacao  implements java.io.Serializable {
    private java.lang.String USUARIO;

    private java.lang.String SENHA_MD5;

    public Autenticacao() {
    }

    public Autenticacao(
           java.lang.String USUARIO,
           java.lang.String SENHA_MD5) {
           this.USUARIO = USUARIO;
           this.SENHA_MD5 = SENHA_MD5;
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
     * Gets the SENHA_MD5 value for this Autenticacao.
     * 
     * @return SENHA_MD5
     */
    public java.lang.String getSENHA_MD5() {
        return SENHA_MD5;
    }


    /**
     * Sets the SENHA_MD5 value for this Autenticacao.
     * 
     * @param SENHA_MD5
     */
    public void setSENHA_MD5(java.lang.String SENHA_MD5) {
        this.SENHA_MD5 = SENHA_MD5;
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
            ((this.SENHA_MD5==null && other.getSENHA_MD5()==null) || 
             (this.SENHA_MD5!=null &&
              this.SENHA_MD5.equals(other.getSENHA_MD5())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Autenticacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "Autenticacao"));
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
