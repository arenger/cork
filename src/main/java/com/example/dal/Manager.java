package com.example.dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Manager {
   private static EntityManagerFactory emFactory;

   private Manager() {}

   public static EntityManager createEntityManager() {
      return emFactory.createEntityManager();
   }

   public static synchronized void init() {
      emFactory = Persistence.createEntityManagerFactory("emaps");
   }

   public static synchronized void shutdown() {
      if (emFactory != null) {
         emFactory.close();
      }
   }
}
