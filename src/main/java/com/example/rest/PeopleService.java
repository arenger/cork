package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dal.DalException;
import com.example.dal.PersonKeeper;
import com.example.dto.Person;

@Path("/person")
public class PeopleService {

   private static final Logger LOG
      = LoggerFactory.getLogger(PeopleService.class);

   @POST
   @Path("addOrUpdate")
   @Consumes("application/json")
   public Person addOrUpdate(Person person) {
      try {
         PersonKeeper pk = new PersonKeeper();
         pk.save(person);
      } catch (DalException e) {
         LOG.error("Problem saving person", e);
         person = null;
      }
      return person;
   }
}
