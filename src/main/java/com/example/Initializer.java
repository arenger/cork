package com.example;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dal.DalException;
import com.example.dal.DefaultSource;

public class Initializer implements ServletContextListener {

   private static final Logger LOG
      = LoggerFactory.getLogger(Initializer.class);

   @Override
   public void contextInitialized(ServletContextEvent event) {
      try {
         DefaultSource.init();
         LOG.info("Default DataSource initialized.");
      } catch (DalException e) {
         LOG.error("Problem initializing default DataSource", e);
      }
   }

   @Override
   public void contextDestroyed(ServletContextEvent event) {}
}
