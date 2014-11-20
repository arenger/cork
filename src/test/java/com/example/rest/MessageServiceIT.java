package com.example.rest;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dal.DalException;
import com.example.dal.Manager;
import com.example.dto.Message;
import com.example.dto.Person;

public class MessageServiceIT {

   private static final Logger LOG = 
      LoggerFactory.getLogger(MessageServiceIT.class);

   @BeforeClass
   public static void init() {
      Manager.init();
   }

   @Test
   public void createAndPost() throws DalException {
      PeopleService ps = new PeopleService();
      Person p = new Person();
      p.setName("Ted");

      int fromId = ps.addOrUpdate(p).getId();
      p = new Person();
      p.setName("Tito");
      int toId = ps.addOrUpdate(p).getId();

      MessageService ms = new MessageService();
      Message m = new Message();
      m.setContent("Hi Tito!");
      m.setFromPersonId(fromId);
      m.setToPersonId(toId);
      LOG.info("sending message: {}", m);
      ms.add(m);

      Assert.assertEquals("60bff48174338ba6f3d8281391a71701\n",
            ms.md5from(fromId));

      ms.add(m);
   }

   @AfterClass
   public static void teardown() {
      Manager.shutdown();
   }

}
