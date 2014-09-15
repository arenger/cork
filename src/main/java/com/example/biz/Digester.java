package com.example.biz;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dto.Message;

public class Digester {
   private static final Logger LOG
      = LoggerFactory.getLogger(Digester.class);

   public String md5(List<Message> messages) {
      if ((messages == null) || (messages.size() == 0)) {
         return "";
      }
      String hash = "error";
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         StringBuilder sb = new StringBuilder();
         for (Message m : messages) {
            sb.append(m.getContent());
         }
         BigInteger bi = new BigInteger(1,
            md.digest(sb.toString().getBytes()));
         hash = bi.toString(16);
      } catch (NoSuchAlgorithmException e) {
         LOG.error("MD5 DNE.  That's odd.");
      }
      return hash;
   }
}
