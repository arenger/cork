package com.example.rest;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/message")
public class MessageService {

   private static final Logger LOG
      = LoggerFactory.getLogger(MessageService.class);

}
