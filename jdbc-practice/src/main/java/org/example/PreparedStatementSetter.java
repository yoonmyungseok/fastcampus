package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
  void setPreparedStatement(PreparedStatement pstmt) throws SQLException;
}