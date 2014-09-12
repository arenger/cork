package com.example.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DefaultSource {
   private static DataSource ds;

   private DefaultSource() {}

   public static DataSource get() {
      return ds;
   }

   public static synchronized void init() throws DalException {
      if (ds == null) {
         try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:/jdbc/DocumentStoreDS");
         } catch (NamingException e) {
            throw new DalException(e);
         }
      }
   }
}
