package com.example.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.example.dto.Message;

public class MessageKeeper {

   public void add(Message message) throws DalException {
      EntityManager     em = null;
      EntityTransaction tx = null;
      try {
         em = Manager.createEntityManager();
         tx = em.getTransaction();
         tx.begin();
         em.persist(message);
         tx.commit();
      } catch (Exception e) {
         if ((tx != null) && (tx.isActive())) {
            tx.rollback();
         }
         throw new DalException(e);
      } finally {
         if ((em != null) && em.isOpen()) {
            em.close();
         }
      }
   }

   public List<Message> getMessagesFrom(int personId) throws DalException {
      List<Message> ret = null;
      EntityManager em  = null;
      try {
         em = Manager.createEntityManager();
         TypedQuery<Message> q = em.createQuery(
               "SELECT m from Message m where from_pid = :pid", Message.class);
         q.setParameter("pid", personId);
         ret = q.getResultList();
      } finally {
         if ((em != null) && em.isOpen()) {
            em.close();
         }
      }
      return ret;
   }

   public List<Message> getMessagesTo(int personId) throws DalException {
      List<Message> ret = null;
      EntityManager em  = null;
      try {
         em = Manager.createEntityManager();
         TypedQuery<Message> q = em.createQuery(
               "SELECT m from Message m where to_pid = :pid", Message.class);
         q.setParameter("pid", personId);
         ret = q.getResultList();
      } finally {
         if ((em != null) && em.isOpen()) {
            em.close();
         }
      }
      return ret;
   }
}
