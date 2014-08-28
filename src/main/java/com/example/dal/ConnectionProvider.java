package com.example.dal;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
   Connection getConnection() throws SQLException;
}
