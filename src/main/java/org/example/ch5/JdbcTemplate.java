package org.example.ch5;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.ch5.ConnectionManage.getConnection;

public class JdbcTemplate {

    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;

        try{
            connection = getConnection();
            pstat = connection.prepareStatement(sql);
            pss.setter(pstat);

            pstat.executeUpdate();
        }finally {
            if (pstat != null) {
                pstat.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Object executeQuery(String userId, String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            pstat = connection.prepareStatement(sql);
            pss.setter(pstat);

            rs = pstat.executeQuery();

            Object obj = null;
            if (rs.next()) {
                return rowMapper.map(rs);
            }
            return null;
        }finally {
            if (rs != null) {
                rs.close();
            }

            if (pstat != null) {
                pstat.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
