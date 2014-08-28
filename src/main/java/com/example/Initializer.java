package com.example;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dal.DalException;
import com.example.dal.DefaultPool;

public class Initializer implements ServletContextListener {

   private static final Logger LOG
      = LoggerFactory.getLogger(Initializer.class);

   @Override
   public void contextInitialized(ServletContextEvent event) {
      try {
         DefaultPool.init();
         LOG.info("Default pool initialized.");
      } catch (DalException e) {
         LOG.error("Problem initializing default pool", e);
      }
   }

   @Override
   public void contextDestroyed(ServletContextEvent event) {}
}
