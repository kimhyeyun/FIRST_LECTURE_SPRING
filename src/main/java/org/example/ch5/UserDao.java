package org.example.ch5;

import java.sql.*;

import static org.example.ch5.ConnectionManage.getConnection;

public class UserDao {

    public void create(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.executeUpdate(user, sql, pstat -> {
            pstat.setString(1, user.getUserId());
            pstat.setString(2, user.getPassword());
            pstat.setString(3, user.getName());
            pstat.setString(4, user.getEmail());
        });
    }
    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userId = ?";

        return (User) jdbcTemplate.executeQuery(userId, sql, pstat -> pstat.setString(1, userId), new RowMapper() {
            @Override
            public Object map(ResultSet resultSet) throws SQLException {
                return new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        });
    }
}
