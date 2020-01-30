package hotel.dao;


import hotel.bean.User;
import hotel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImp implements UserDao {
    @Override
    public User findByLoginNameAndPass(String loginname, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM bighotel_user WHERE LOGIN_NAME = ?  AND PASSWORD =? AND DISABLED = 0";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginname);
            preparedStatement.setString(2,password);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                Integer userId = resultSet.getInt("ID");
                User user = new User();

                user.setId(userId);
                user.setCreateDate(resultSet.getTimestamp("CREATE_DATE"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setLoginName(resultSet.getString("LOGIN_NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setDisabled(resultSet.getInt("DISABLED"));
                user.setPhone(resultSet.getString("PHONE"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return null;
    }

    @Override
    public User findByLoginName(String loginName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM bighotel_user WHERE LOGIN_NAME = ? AND DISABLED = 0";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginName);


            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                Integer userId = resultSet.getInt("ID");
                User user = new User();

                user.setId(userId);
                user.setCreateDate(resultSet.getTimestamp("CREATE_DATE"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setLoginName(resultSet.getString("LOGIN_NAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setDisabled(resultSet.getInt("DISABLED"));
                user.setPhone(resultSet.getString("PHONE"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }



        return null;
    }

    @Override
    public void save(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "INSERT INTO bighotel_user(LOGIN_NAME,PASSWORD,EMAIL,PHONE,CREATE_DATE) VALUES(?,?,?,?,NOW())";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLoginName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPhone());

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
    }
}
