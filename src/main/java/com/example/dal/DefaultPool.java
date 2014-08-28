package com.example.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class DefaultPool implements ConnectionProvider {
   private static DefaultPool me;

   private final DataSource ds;

   private DefaultPool() throws DalException {
      try {
         Context ctx = new InitialContext();
         ds = (DataSource)ctx.lookup("java:/jdbc/DocumentStoreDS");
      } catch (NamingException e) {
         throw new DalException(e);
      }
   }

   public static DefaultPool getInstance() {
      return me;
   }

   public static synchronized void init() throws DalException {
      if (me == null) {
         me = new DefaultPool();
      }
   }

   public Connection getConnection() throws SQLException {
      return ds.getConnection();
   }
}
