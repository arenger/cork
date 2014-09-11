package com.example.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.dto.Person;

//TODO modify this example to use spring and hibernate instead

public class PersonKeeper {
   private static final String INS =
      "insert into person (id, name) values (?, ?)";
   private static final String UPD =
      "update person set name = ? where id = ?";
   private static final String MAXID =
      "select max(id) from person"; // normally an oracle sequence,
                                    // but for simplicity...

   private ConnectionProvider pool;
   private static Integer nextId = null;

   public PersonKeeper() {
      pool = DefaultPool.getInstance();
   }

   public PersonKeeper(ConnectionProvider pool) {
      this.pool = pool;
   }

   private static synchronized int getNextId(Connection conn)
      throws SQLException {
      if (nextId == null) {
         try (PreparedStatement ps = conn.prepareStatement(MAXID)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               nextId = rs.getInt(1) + 1;
            } else {
               nextId = 1;
            }
         }
      }
      return nextId++;
   }

   public void save(Person person) throws DalException {
      if (person.getId() == null) {
         add(person);
      } else {
         update(person);
      }
   }

   public void add(Person person) throws DalException {
      try (Connection conn = pool.getConnection();
           PreparedStatement ps = conn.prepareStatement(INS)) {
         if (person.getId() != null) {
            throw new IllegalArgumentException("person.id should be null");
         }
         person.setId(getNextId(conn));
         ps.setInt(1, person.getId());
         ps.setString(2, person.getName());
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DalException(e);
      }
   }

   public void update(Person person) throws DalException {
      try (Connection conn = pool.getConnection();
           PreparedStatement ps = conn.prepareStatement(UPD)) {
         ps.setString(1, person.getName());
         ps.setInt(2, person.getId());
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DalException(e);
      }
   }
}
