package com.example.dal;

import java.io.InputStreamReader;
import java.sql.Connection;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMessageKeeper {
   private static final String JDBC_DRIVER = "org.h2.Driver";
   private static final String JDBC_URL    =
      "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
   private static final String USER = "sa";
   private static final String PASS = "";
   private static final String SCHEMA_FILE = "h2.sql";

   @BeforeClass
   public static void createSchema() throws Exception {
      Class.forName(JDBC_DRIVER);
      try (Connection conn = dataSource().getConnection()) {
         InputStreamReader in = new InputStreamReader(
            TestMessageKeeper.class.getResourceAsStream(SCHEMA_FILE));
         RunScript.execute(conn, in);
      }
   }

   private static DataSource dataSource() {
      JdbcDataSource dataSource = new JdbcDataSource();
      dataSource.setURL(JDBC_URL);
      dataSource.setUser(USER);
      dataSource.setPassword(PASS);
      return dataSource;
   }

   //@Test
   //public testGet
}
