package hotel.dao;

import hotel.bean.FoodType;
import hotel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodTypeDaoImp implements FoodTypeDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<FoodType> findAll() {
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "select * from food_type where disabled = 0";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            List<FoodType> foodTypes = new ArrayList<>();
            while (resultSet.next()) {          //省略表头信息
                FoodType foodType = new FoodType();
//                获取每一列的值
                foodType.setId(resultSet.getInt("id"));
                foodType.setTypeName(resultSet.getString("type_name"));
                foodType.setDisabled(resultSet.getInt("disabled"));
                foodType.setCreateDate(resultSet.getTimestamp("create_date"));
                foodType.setUpdateDate(resultSet.getTimestamp("update_date"));

                foodTypes.add(foodType);

            }
            return foodTypes;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }
}
