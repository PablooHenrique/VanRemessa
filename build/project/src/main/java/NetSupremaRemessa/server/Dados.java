/**
 * Dados.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaRemessa.server;

public class Dados  implements java.io.Serializable {
    private java.lang.String EMAIL_NOTIFICACAO;

    private java.lang.String FORMATO;

    private java.lang.String NOME_ARQUIVO;

    private java.lang.String REMESSA;

    public Dados() {
    }

    public Dados(
           java.lang.String EMAIL_NOTIFICACAO,
           java.lang.String FORMATO,
           java.lang.String NOME_ARQUIVO,
           java.lang.String REMESSA) {
           this.EMAIL_NOTIFICACAO = EMAIL_NOTIFICACAO;
           this.FORMATO = FORMATO;
           this.NOME_ARQUIVO = NOME_ARQUIVO;
           this.REMESSA = REMESSA;
    }


    /**
     * Gets the EMAIL_NOTIFICACAO value for this Dados.
     * 
     * @return EMAIL_NOTIFICACAO
     */
    public java.lang.String getEMAIL_NOTIFICACAO() {
        return EMAIL_NOTIFICACAO;
    }


    /**
     * Sets the EMAIL_NOTIFICACAO value for this Dados.
     * 
     * @param EMAIL_NOTIFICACAO
     */
    public void setEMAIL_NOTIFICACAO(java.lang.String EMAIL_NOTIFICACAO) {
        this.EMAIL_NOTIFICACAO = EMAIL_NOTIFICACAO;
    }


    /**
     * Gets the FORMATO value for this Dados.
     * 
     * @return FORMATO
     */
    public java.lang.String getFORMATO() {
        return FORMATO;
    }


    /**
     * Sets the FORMATO value for this Dados.
     * 
     * @param FORMATO
     */
    public void setFORMATO(java.lang.String FORMATO) {
        this.FORMATO = FORMATO;
    }


    /**
     * Gets the NOME_ARQUIVO value for this Dados.
     * 
     * @return NOME_ARQUIVO
     */
    public java.lang.String getNOME_ARQUIVO() {
        return NOME_ARQUIVO;
    }


    /**
     * Sets the NOME_ARQUIVO value for this Dados.
     * 
     * @param NOME_ARQUIVO
     */
    public void setNOME_ARQUIVO(java.lang.String NOME_ARQUIVO) {
        this.NOME_ARQUIVO = NOME_ARQUIVO;
    }


    /**
     * Gets the REMESSA value for this Dados.
     * 
     * @return REMESSA
     */
    public java.lang.String getREMESSA() {
        return REMESSA;
    }


    /**
     * Sets the REMESSA value for this Dados.
     * 
     * @param REMESSA
     */
    public void setREMESSA(java.lang.String REMESSA) {
        this.REMESSA = REMESSA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dados)) return false;
        Dados other = (Dados) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.EMAIL_NOTIFICACAO==null && other.getEMAIL_NOTIFICACAO()==null) || 
             (this.EMAIL_NOTIFICACAO!=null &&
              this.EMAIL_NOTIFICACAO.equals(other.getEMAIL_NOTIFICACAO()))) &&
            ((this.FORMATO==null && other.getFORMATO()==null) || 
             (this.FORMATO!=null &&
              this.FORMATO.equals(other.getFORMATO()))) &&
            ((this.NOME_ARQUIVO==null && other.getNOME_ARQUIVO()==null) || 
             (this.NOME_ARQUIVO!=null &&
              this.NOME_ARQUIVO.equals(other.getNOME_ARQUIVO()))) &&
            ((this.REMESSA==null && other.getREMESSA()==null) || 
             (this.REMESSA!=null &&
              this.REMESSA.equals(other.getREMESSA())));
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
        if (getEMAIL_NOTIFICACAO() != null) {
            _hashCode += getEMAIL_NOTIFICACAO().hashCode();
        }
        if (getFORMATO() != null) {
            _hashCode += getFORMATO().hashCode();
        }
        if (getNOME_ARQUIVO() != null) {
            _hashCode += getNOME_ARQUIVO().hashCode();
        }
        if (getREMESSA() != null) {
            _hashCode += getREMESSA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dados.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaRemessa", "Dados"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAIL_NOTIFICACAO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EMAIL_NOTIFICACAO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMATO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FORMATO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOME_ARQUIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NOME_ARQUIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REMESSA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REMESSA"));
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
