/**
 * DadosConsulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package NetSupremaConsultaProcessamento.server;

public class DadosConsulta  implements java.io.Serializable {
    private java.lang.String CODIGO_CEDENTE;

    private java.lang.String CODIGO_CEDENTE_DV;

    private java.lang.String CONTA_CORRENTE;

    private java.lang.String CONTA_CORRENTE_DV;

    private java.lang.String CODIGO_LOTE_AGENDAMENTO;

    public DadosConsulta() {
    }

    public DadosConsulta(
           java.lang.String CODIGO_CEDENTE,
           java.lang.String CODIGO_CEDENTE_DV,
           java.lang.String CONTA_CORRENTE,
           java.lang.String CONTA_CORRENTE_DV,
           java.lang.String CODIGO_LOTE_AGENDAMENTO) {
           this.CODIGO_CEDENTE = CODIGO_CEDENTE;
           this.CODIGO_CEDENTE_DV = CODIGO_CEDENTE_DV;
           this.CONTA_CORRENTE = CONTA_CORRENTE;
           this.CONTA_CORRENTE_DV = CONTA_CORRENTE_DV;
           this.CODIGO_LOTE_AGENDAMENTO = CODIGO_LOTE_AGENDAMENTO;
    }


    /**
     * Gets the CODIGO_CEDENTE value for this DadosConsulta.
     * 
     * @return CODIGO_CEDENTE
     */
    public java.lang.String getCODIGO_CEDENTE() {
        return CODIGO_CEDENTE;
    }


    /**
     * Sets the CODIGO_CEDENTE value for this DadosConsulta.
     * 
     * @param CODIGO_CEDENTE
     */
    public void setCODIGO_CEDENTE(java.lang.String CODIGO_CEDENTE) {
        this.CODIGO_CEDENTE = CODIGO_CEDENTE;
    }


    /**
     * Gets the CODIGO_CEDENTE_DV value for this DadosConsulta.
     * 
     * @return CODIGO_CEDENTE_DV
     */
    public java.lang.String getCODIGO_CEDENTE_DV() {
        return CODIGO_CEDENTE_DV;
    }


    /**
     * Sets the CODIGO_CEDENTE_DV value for this DadosConsulta.
     * 
     * @param CODIGO_CEDENTE_DV
     */
    public void setCODIGO_CEDENTE_DV(java.lang.String CODIGO_CEDENTE_DV) {
        this.CODIGO_CEDENTE_DV = CODIGO_CEDENTE_DV;
    }


    /**
     * Gets the CONTA_CORRENTE value for this DadosConsulta.
     * 
     * @return CONTA_CORRENTE
     */
    public java.lang.String getCONTA_CORRENTE() {
        return CONTA_CORRENTE;
    }


    /**
     * Sets the CONTA_CORRENTE value for this DadosConsulta.
     * 
     * @param CONTA_CORRENTE
     */
    public void setCONTA_CORRENTE(java.lang.String CONTA_CORRENTE) {
        this.CONTA_CORRENTE = CONTA_CORRENTE;
    }


    /**
     * Gets the CONTA_CORRENTE_DV value for this DadosConsulta.
     * 
     * @return CONTA_CORRENTE_DV
     */
    public java.lang.String getCONTA_CORRENTE_DV() {
        return CONTA_CORRENTE_DV;
    }


    /**
     * Sets the CONTA_CORRENTE_DV value for this DadosConsulta.
     * 
     * @param CONTA_CORRENTE_DV
     */
    public void setCONTA_CORRENTE_DV(java.lang.String CONTA_CORRENTE_DV) {
        this.CONTA_CORRENTE_DV = CONTA_CORRENTE_DV;
    }


    /**
     * Gets the CODIGO_LOTE_AGENDAMENTO value for this DadosConsulta.
     * 
     * @return CODIGO_LOTE_AGENDAMENTO
     */
    public java.lang.String getCODIGO_LOTE_AGENDAMENTO() {
        return CODIGO_LOTE_AGENDAMENTO;
    }


    /**
     * Sets the CODIGO_LOTE_AGENDAMENTO value for this DadosConsulta.
     * 
     * @param CODIGO_LOTE_AGENDAMENTO
     */
    public void setCODIGO_LOTE_AGENDAMENTO(java.lang.String CODIGO_LOTE_AGENDAMENTO) {
        this.CODIGO_LOTE_AGENDAMENTO = CODIGO_LOTE_AGENDAMENTO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DadosConsulta)) return false;
        DadosConsulta other = (DadosConsulta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CODIGO_CEDENTE==null && other.getCODIGO_CEDENTE()==null) || 
             (this.CODIGO_CEDENTE!=null &&
              this.CODIGO_CEDENTE.equals(other.getCODIGO_CEDENTE()))) &&
            ((this.CODIGO_CEDENTE_DV==null && other.getCODIGO_CEDENTE_DV()==null) || 
             (this.CODIGO_CEDENTE_DV!=null &&
              this.CODIGO_CEDENTE_DV.equals(other.getCODIGO_CEDENTE_DV()))) &&
            ((this.CONTA_CORRENTE==null && other.getCONTA_CORRENTE()==null) || 
             (this.CONTA_CORRENTE!=null &&
              this.CONTA_CORRENTE.equals(other.getCONTA_CORRENTE()))) &&
            ((this.CONTA_CORRENTE_DV==null && other.getCONTA_CORRENTE_DV()==null) || 
             (this.CONTA_CORRENTE_DV!=null &&
              this.CONTA_CORRENTE_DV.equals(other.getCONTA_CORRENTE_DV()))) &&
            ((this.CODIGO_LOTE_AGENDAMENTO==null && other.getCODIGO_LOTE_AGENDAMENTO()==null) || 
             (this.CODIGO_LOTE_AGENDAMENTO!=null &&
              this.CODIGO_LOTE_AGENDAMENTO.equals(other.getCODIGO_LOTE_AGENDAMENTO())));
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
        if (getCODIGO_CEDENTE() != null) {
            _hashCode += getCODIGO_CEDENTE().hashCode();
        }
        if (getCODIGO_CEDENTE_DV() != null) {
            _hashCode += getCODIGO_CEDENTE_DV().hashCode();
        }
        if (getCONTA_CORRENTE() != null) {
            _hashCode += getCONTA_CORRENTE().hashCode();
        }
        if (getCONTA_CORRENTE_DV() != null) {
            _hashCode += getCONTA_CORRENTE_DV().hashCode();
        }
        if (getCODIGO_LOTE_AGENDAMENTO() != null) {
            _hashCode += getCODIGO_LOTE_AGENDAMENTO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DadosConsulta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:server.NetSupremaConsultaProcessamento", "DadosConsulta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_CEDENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_CEDENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_CEDENTE_DV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_CEDENTE_DV"));
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
        elemField.setFieldName("CONTA_CORRENTE_DV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTA_CORRENTE_DV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGO_LOTE_AGENDAMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODIGO_LOTE_AGENDAMENTO"));
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
