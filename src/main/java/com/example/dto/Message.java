package com.example.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@XmlRootElement(name = "person")
public class Message implements Comparable<Message> {
   private Integer id;
   private Integer fromPersonId;
   private Integer toPersonId;
   private String  content;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @XmlElement
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Basic
   @Column(name = "from_pid")
   @XmlElement
   public Integer getFromPersonId() {
      return fromPersonId;
   }

   public void setFromPersonId(Integer fromPersonId) {
      this.fromPersonId = fromPersonId;
   }

   @Basic
   @Column(name = "to_pid")
   @XmlElement
   public Integer getToPersonId() {
      return toPersonId;
   }

   public void setToPersonId(Integer toPersonId) {
      this.toPersonId = toPersonId;
   }

   @Basic
   @XmlElement
   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   @Override
   public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
   }

   @Override
   public int hashCode() {
      return HashCodeBuilder.reflectionHashCode(this);
   }

   @Override
   public int compareTo(Message m) {
      return new CompareToBuilder()
         .append(fromPersonId, m.fromPersonId)
         .append(content, m.content)
         .toComparison();
   }

   @Override
   public String toString() {
      return ToStringBuilder.reflectionToString(this);
   }
}
