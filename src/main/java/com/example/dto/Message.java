package com.example.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
public class Message {
   private int    id;
   private int    fromPersonId;
   private int    toPersonId;
   private String content;

   @XmlElement
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @XmlElement
   public int getFromPersonId() {
      return fromPersonId;
   }

   public void setFromPersonId(int fromPersonId) {
      this.fromPersonId = fromPersonId;
   }

   @XmlElement
   public int getToPersonId() {
      return toPersonId;
   }

   public void setToPersonId(int toPersonId) {
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
