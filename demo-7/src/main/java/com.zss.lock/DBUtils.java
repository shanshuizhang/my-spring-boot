package com.zss.lock;

import java.sql.*;

public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/zsscode?Unicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "root";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void closeConnection(Connection connection){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FunctionalInterface
    private interface SqlExec<T>{
        T exec(Connection connection) throws Exception;
    }

    private static <T> T exec(SqlExec<T> sqlExec) throws Exception {
        Connection connection = getConnection();
        try {
            return sqlExec.exec(connection);
        } finally {
            closeConnection(connection);
        }
    }

    public static LockModel get(String lockKey) throws Exception {
        return exec(conn -> {
            String sql = " select * from t_lock t where t.lock_key = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++,lockKey);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return LockModel.builder()
                        .lockKey(lockKey)
                        .requestId(rs.getString("request_id"))
                        .holdTime(rs.getLong("hold_time"))
                        .version(rs.getInt("version"))
                        .lockCount(rs.getInt("lock_count"))
                        .build();
            }
            return null;
        });
    }

    public static int insert(LockModel lockModel) throws Exception {
        return exec(conn -> {
            String sql = "insert into t_lock (lock_key, request_id, lock_count, hold_time, version) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getLockKey());
            ps.setString(colIndex++, lockModel.getRequestId());
            ps.setInt(colIndex++, lockModel.getLockCount());
            ps.setLong(colIndex++, lockModel.getHoldTime());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }

    public static int update(LockModel lockModel) throws Exception {
        return exec(conn ->{
            String sql = "update t_lock SET request_id = ?,lock_count = ?,hold_time = ?,version = version + 1 WHERE lock_key = ? AND  version = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            int colIndex = 1;
            ps.setString(colIndex++, lockModel.getRequestId());
            ps.setInt(colIndex++, lockModel.getLockCount());
            ps.setLong(colIndex++, lockModel.getHoldTime());
            ps.setString(colIndex++, lockModel.getLockKey());
            ps.setInt(colIndex++, lockModel.getVersion());
            return ps.executeUpdate();
        });
    }
}
