package com.example;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dal.Manager;

public class Initializer implements ServletContextListener {

   private static final Logger LOG
      = LoggerFactory.getLogger(Initializer.class);

   @Override
   public void contextInitialized(ServletContextEvent event) {
      Manager.init();
      LOG.debug("dal.Manager initialized.");
   }

   @Override
   public void contextDestroyed(ServletContextEvent event) {
      Manager.shutdown();
   }
}
