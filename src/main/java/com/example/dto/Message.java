package com.example.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Message {
   private Integer id;
   private Integer fromPersonId;
   private Integer toPersonId;
   private String  content;

   @XmlElement
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @XmlElement
   public Integer getFromPersonId() {
      return fromPersonId;
   }

   public void setFromPersonId(Integer fromPersonId) {
      this.fromPersonId = fromPersonId;
   }

   @XmlElement
   public Integer getToPersonId() {
      return toPersonId;
   }

   public void setToPersonId(Integer toPersonId) {
      this.toPersonId = toPersonId;
   }

   @XmlElement
   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }
}
