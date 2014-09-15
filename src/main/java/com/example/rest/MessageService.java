package com.example.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.biz.Digester;
import com.example.dal.DalException;
import com.example.dal.MessageKeeper;
import com.example.dto.Message;

@Path("/message")
public class MessageService {

   private static final Logger LOG
      = LoggerFactory.getLogger(MessageService.class);

   private final MessageKeeper messageKeeper;

   public MessageService() {
      messageKeeper = new MessageKeeper();
   }

   public MessageService(MessageKeeper messageKeeper) {
      this.messageKeeper = messageKeeper;
   }

   @POST
   @Path("add")
   @Consumes("application/json")
   public Message add(Message message) {
      try {
         messageKeeper.add(message);
      } catch (DalException e) {
         LOG.error("Problem adding message", e);
         message = null;
      }
      return message;
   }

   @GET
   @Path("getFrom/{personId}")
   @Consumes("text/plain")
   public Set<Message> getFrom(@PathParam("personId") int personId) {
      Set<Message> ret = null;
      try {
         ret = messageKeeper.getMessagesFrom(personId);
      } catch (DalException e) {
         LOG.error("Problem getting messages from {}", personId, e);
      }
      return ret;
   }

   @GET
   @Path("getTo/{personId}")
   @Consumes("text/plain")
   public Set<Message> getTo(@PathParam("personId") int personId) {
      Set<Message> ret = null;
      try {
         ret = messageKeeper.getMessagesTo(personId);
      } catch (DalException e) {
         LOG.error("Problem getting messages from {}", personId, e);
      }
      return ret;
   }

   @GET
   @Path("md5/sentFrom/{personId}")
   @Consumes("text/plain")
   public String md5from(@PathParam("personId") int personId) {
      String md5 = null;
      try {
         Digester d = new Digester();
         List<Message> ml = new ArrayList<Message>();
         ml.addAll(messageKeeper.getMessagesFrom(personId));
         Collections.sort(ml);
         md5 = d.md5(ml) + "\n";
      } catch (DalException e) {
         LOG.error("Problem getting messages from {}", personId, e);
      }
      return md5;
   }
}
