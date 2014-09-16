package com.example.dal;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Set;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.dto.Message;

public class MessageKeeperTest {
   private static final String JDBC_DRIVER = "org.h2.Driver";
   private static final String JDBC_URL    =
      "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
   private static final String USER = "sa";
   private static final String PASS = "";
   private static final String SCHEMA_FILE = "h2.sql";
   private static final String DATASET1    = "set1.xml";

   @BeforeClass
   public static void createSchema() throws Exception {
      Class.forName(JDBC_DRIVER);
      try (Connection conn = dataSource().getConnection();
           InputStreamReader in = new InputStreamReader(
           MessageKeeperTest.class.getResourceAsStream(SCHEMA_FILE))) {
         RunScript.execute(conn, in);
      }
   }

   private static void load(String filename) throws Exception {
      IDataSet ids = new FlatXmlDataSetBuilder().build(
         MessageKeeperTest.class.getResourceAsStream(filename));
      IDatabaseTester databaseTester =
         new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASS);
      databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
      databaseTester.setDataSet(ids);
      databaseTester.onSetup();
   }

   private static DataSource dataSource() {
      JdbcDataSource dataSource = new JdbcDataSource();
      dataSource.setURL(JDBC_URL);
      dataSource.setUser(USER);
      dataSource.setPassword(PASS);
      return dataSource;
   }

   @Test
   public void testGetMessagesTo() throws Exception {
      load(DATASET1);
      MessageKeeper mk = new MessageKeeper(dataSource());
      Set<Message> ms = mk.getMessagesTo(2);
      Assert.assertEquals("Expected count", 2, ms.size());

      Message m = new Message();
      m.setId(1);
      m.setFromPersonId(1);
      m.setContent("hello world!");
      Assert.assertTrue("Expecting " + m, ms.contains(m));

      m = new Message();
      m.setId(2);
      m.setFromPersonId(1);
      m.setToPersonId(2);
      m.setContent("hello!");
      Assert.assertTrue("Expecting " + m, ms.contains(m));
   }
}
