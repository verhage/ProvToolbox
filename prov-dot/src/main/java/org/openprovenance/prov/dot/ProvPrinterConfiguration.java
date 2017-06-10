//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.05 at 10:17:37 PM BST 
//


package org.openprovenance.prov.dot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openprovenance.apache.commons.lang.builder.EqualsBuilder;
import org.openprovenance.apache.commons.lang.builder.HashCodeBuilder;
import org.openprovenance.apache.commons.lang.builder.ToStringBuilder;
import org.openprovenance.prov.xml.builder.Equals;
import org.openprovenance.prov.xml.builder.HashCode;
import org.openprovenance.prov.xml.builder.ToString;
import org.openprovenance.prov.xml.builder.JAXBEqualsBuilder;
import org.openprovenance.prov.xml.builder.JAXBHashCodeBuilder;
import org.openprovenance.prov.xml.builder.JAXBToStringBuilder;



/**
 * <p>Java class for ProvPrinterConfiguration complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProvPrinterConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="relations" type="{http://openprovenance.org/model/opmPrinterConfig}RelationStyleMap" minOccurs="0"/>
 *         &lt;element name="activities" type="{http://openprovenance.org/model/opmPrinterConfig}ActivityMap" minOccurs="0"/>
 *         &lt;element name="entities" type="{http://openprovenance.org/model/opmPrinterConfig}EntityMap" minOccurs="0"/>
 *         &lt;element name="agents" type="{http://openprovenance.org/model/opmPrinterConfig}AgentMap" minOccurs="0"/>
 *         &lt;element name="accounts" type="{http://openprovenance.org/model/opmPrinterConfig}AccountMap" minOccurs="0"/>
 *         &lt;element name="displayEntityValue" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="graphName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProvPrinterConfiguration", namespace = "http://openprovenance.org/model/opmPrinterConfig", propOrder = {
    "relations",
    "activities",
    "entities",
    "agents",
    "accounts",
    "displayEntityValue",
    "graphName"
})
public class ProvPrinterConfiguration
    implements Equals, HashCode, ToString
{

    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected RelationStyleMap relations;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected ActivityMap activities;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected EntityMap entities;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected AgentMap agents;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected AccountMap accounts;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected Boolean displayEntityValue;
    @XmlElement(namespace = "http://openprovenance.org/model/opmPrinterConfig")
    protected String graphName;

    /**
     * Gets the value of the relations property.
     * 
     * @return
     *     possible object is
     *     {@link RelationStyleMap }
     *     
     */
    public RelationStyleMap getRelations() {
        return relations;
    }

    /**
     * Sets the value of the relations property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationStyleMap }
     *     
     */
    public void setRelations(RelationStyleMap value) {
        this.relations = value;
    }

    /**
     * Gets the value of the activities property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityMap }
     *     
     */
    public ActivityMap getActivities() {
        return activities;
    }

    /**
     * Sets the value of the activities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityMap }
     *     
     */
    public void setActivities(ActivityMap value) {
        this.activities = value;
    }

    /**
     * Gets the value of the entities property.
     * 
     * @return
     *     possible object is
     *     {@link EntityMap }
     *     
     */
    public EntityMap getEntities() {
        return entities;
    }

    /**
     * Sets the value of the entities property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityMap }
     *     
     */
    public void setEntities(EntityMap value) {
        this.entities = value;
    }

    /**
     * Gets the value of the agents property.
     * 
     * @return
     *     possible object is
     *     {@link AgentMap }
     *     
     */
    public AgentMap getAgents() {
        return agents;
    }

    /**
     * Sets the value of the agents property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentMap }
     *     
     */
    public void setAgents(AgentMap value) {
        this.agents = value;
    }

    /**
     * Gets the value of the accounts property.
     * 
     * @return
     *     possible object is
     *     {@link AccountMap }
     *     
     */
    public AccountMap getAccounts() {
        return accounts;
    }

    /**
     * Sets the value of the accounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountMap }
     *     
     */
    public void setAccounts(AccountMap value) {
        this.accounts = value;
    }

    /**
     * Gets the value of the displayEntityValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayEntityValue() {
        return displayEntityValue;
    }

    /**
     * Sets the value of the displayEntityValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayEntityValue(Boolean value) {
        this.displayEntityValue = value;
    }

    /**
     * Gets the value of the graphName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGraphName() {
        return graphName;
    }

    /**
     * Sets the value of the graphName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGraphName(String value) {
        this.graphName = value;
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof ProvPrinterConfiguration)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        final ProvPrinterConfiguration that = ((ProvPrinterConfiguration) object);
        equalsBuilder.append(this.getRelations(), that.getRelations());
        equalsBuilder.append(this.getActivities(), that.getActivities());
        equalsBuilder.append(this.getEntities(), that.getEntities());
        equalsBuilder.append(this.getAgents(), that.getAgents());
        equalsBuilder.append(this.getAccounts(), that.getAccounts());
        equalsBuilder.append(this.isDisplayEntityValue(), that.isDisplayEntityValue());
        equalsBuilder.append(this.getGraphName(), that.getGraphName());
    }

    public boolean equals(Object object) {
        if (!(object instanceof ProvPrinterConfiguration)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        hashCodeBuilder.append(this.getRelations());
        hashCodeBuilder.append(this.getActivities());
        hashCodeBuilder.append(this.getEntities());
        hashCodeBuilder.append(this.getAgents());
        hashCodeBuilder.append(this.getAccounts());
        hashCodeBuilder.append(this.isDisplayEntityValue());
        hashCodeBuilder.append(this.getGraphName());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public void toString(ToStringBuilder toStringBuilder) {
        {
            RelationStyleMap theRelations;
            theRelations = this.getRelations();
            toStringBuilder.append("relations", theRelations);
        }
        {
            ActivityMap theActivities;
            theActivities = this.getActivities();
            toStringBuilder.append("activities", theActivities);
        }
        {
            EntityMap theEntities;
            theEntities = this.getEntities();
            toStringBuilder.append("entities", theEntities);
        }
        {
            AgentMap theAgents;
            theAgents = this.getAgents();
            toStringBuilder.append("agents", theAgents);
        }
        {
            AccountMap theAccounts;
            theAccounts = this.getAccounts();
            toStringBuilder.append("accounts", theAccounts);
        }
        {
            Boolean theDisplayEntityValue;
            theDisplayEntityValue = this.isDisplayEntityValue();
            toStringBuilder.append("displayEntityValue", theDisplayEntityValue);
        }
        {
            String theGraphName;
            theGraphName = this.getGraphName();
            toStringBuilder.append("graphName", theGraphName);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

}
