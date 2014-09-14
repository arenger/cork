package com.example.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Set;
import java.util.HashSet;

import javax.sql.DataSource;

import com.example.dto.Message;

//TODO modify this example to use spring and hibernate instead

public class MessageKeeper {
   private static final String INS =
      "insert into message (id, from_pid, to_pid, content) " +
      "values (?, ?, ?, ?)";
   private static final String SEL_FPID =
      "select * from message where from_pid = ?";
   private static final String SEL_TPID =
      "select * from message where to_pid = ? or to_pid is null";
   private static final String MAXID =
      "select max(id) from message"; // normally an oracle sequence,
                                     // but for simplicity...

   private DataSource ds;
   private static Integer nextId = null;

   public MessageKeeper() {
      ds = DefaultSource.get();
   }

   public MessageKeeper(DataSource ds) {
      this.ds = ds;
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

   public void add(Message message) throws DalException {
      try (Connection conn = ds.getConnection();
           PreparedStatement ps = conn.prepareStatement(INS)) {
         if (message.getId() != null) {
            throw new IllegalArgumentException("message.id should be null");
         }
         if (message.getFromPersonId() == null) {
            throw new IllegalArgumentException(
               "message.to_pid cannot be null");
         }
         message.setId(getNextId(conn));
         ps.setInt(1, message.getId());
         ps.setInt(2, message.getFromPersonId());
         if (message.getToPersonId() != null) {
            ps.setInt(3, message.getToPersonId());
         } else {
            ps.setNull(3, Types.INTEGER);
         }
         ps.setString(4, message.getContent());
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DalException(e);
      }
   }

   public Set<Message> getMessagesTo(int personId) throws DalException {
      return getMessagesTF(personId, SEL_TPID);
   }

   public Set<Message> getMessagesFrom(int personId) throws DalException {
      return getMessagesTF(personId, SEL_FPID);
   }

   private Set<Message> getMessagesTF(int personId, String sql)
      throws DalException {
      Set<Message> ret = new HashSet<Message>();
      try (Connection conn = ds.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setInt(1, personId);
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
            Message m = new Message();
            m.setId(rs.getInt("id"));
            m.setFromPersonId(rs.getInt("from_pid"));
            m.setToPersonId(rs.getInt("to_pid"));
            if (rs.wasNull()) {
               m.setToPersonId(null);
            }
            m.setContent(rs.getString("content"));
            if (rs.wasNull()) {
               m.setContent(null);
            }
            ret.add(m);
         }
      } catch (SQLException e) {
         throw new DalException(e);
      }
      return ret;
   }
}
