package com.example.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.example.dto.Person;

public class PersonKeeper {

   public void save(Person person) throws DalException {
      EntityManager     mgr = null;
      EntityTransaction tx  = null;
      try {
         mgr = Manager.createEntityManager();
         tx = mgr.getTransaction();
         tx.begin();
         mgr.persist(person);
         tx.commit();
      } catch (Exception e) {
         if ((tx != null) && (tx.isActive())) {
            tx.rollback();
         }
         throw new DalException(e);
      } finally {
         if ((mgr != null) && mgr.isOpen()) {
            mgr.close();
         }
      }
   }

}
