package com.example.dto;

public class Message {
   private int    id;
   private int    fromPersonId;
   private int    toPersonId;
   private String content;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getFromPersonId() {
      return fromPersonId;
   }

   public void setFromPersonId(int fromPersonId) {
      this.fromPersonId = fromPersonId;
   }

   public int getToPersonId() {
      return toPersonId;
   }

   public void setToPersonId(int toPersonId) {
      this.toPersonId = toPersonId;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }
}
