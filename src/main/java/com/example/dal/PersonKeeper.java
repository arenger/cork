package com.example.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.dto.Person;

//TODO modify this example to use hibernate instead

public class PersonKeeper {
   private static final String INS =
      "insert into person (id, name) values (?, ?)";
   private static final String UPD =
      "update person set name = ? where id = ?";

   private ConnectionProvider pool;

   public PersonKeeper(ConnectionProvider pool) {
      this.pool = pool;
   }

   private static synchronized int getId() {
      return 1; //TODO
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
         person.setId(getId());
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
