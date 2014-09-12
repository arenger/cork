package com.example.rest;

import java.util.List;

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

   @POST
   @Path("add")
   @Consumes("application/json")
   public Message add(Message message) {
      try {
         MessageKeeper mk = new MessageKeeper();
         mk.add(message);
      } catch (DalException e) {
         LOG.error("Problem adding message", e);
         message = null;
      }
      return message;
   }

   @GET
   @Path("getFrom/{personId}")
   @Consumes("text/plain")
   public List<Message> getFrom(@PathParam("personId") int personId) {
      List<Message> ret = null;
      try {
         MessageKeeper mk = new MessageKeeper();
         ret = mk.getMessagesFrom(personId);
      } catch (DalException e) {
         LOG.error("Problem getting messages from {}", personId, e);
      }
      return ret;
   }

   @GET
   @Path("getTo/{personId}")
   @Consumes("text/plain")
   public List<Message> getTo(@PathParam("personId") int personId) {
      List<Message> ret = null;
      try {
         MessageKeeper mk = new MessageKeeper();
         ret = mk.getMessagesTo(personId);
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
         MessageKeeper mk = new MessageKeeper();
         Digester d = new Digester();
         md5 = d.getMd5(mk.getMessagesFrom(personId)) + "\n";
      } catch (DalException e) {
         LOG.error("Problem getting messages from {}", personId, e);
      }
      return md5;
   }
}
