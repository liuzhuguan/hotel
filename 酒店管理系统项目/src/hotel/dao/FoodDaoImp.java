package hotel.dao;

import hotel.bean.Food;
import hotel.bean.FoodType;
import hotel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImp implements FoodDao {
    @Override
    public List<Food> findByfoodTypeId(Integer foodTypeId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_food WHERE disabled = 0 AND foodType_id = ?";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,foodTypeId);

            resultSet = preparedStatement.executeQuery();

            List<Food> foods = new ArrayList<>();
            while (resultSet.next()) {          //省略表头信息
                Food food = new Food();

                food.setId(resultSet.getInt("id"));
                food.setCreateDate(resultSet.getTimestamp("create_date"));
                food.setUpdateDate(resultSet.getTimestamp("update_date"));
                food.setDisabled(resultSet.getInt("disabled"));
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                foods.add(food);
            }
            return foods;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Food findById(Integer foodId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM hotel_food WHERE id = ?";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,foodId);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                Food food = new Food();

                food.setId(resultSet.getInt("id"));
                food.setCreateDate(resultSet.getTimestamp("create_date"));
                food.setUpdateDate(resultSet.getTimestamp("update_date"));
                food.setDisabled(resultSet.getInt("disabled"));
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                return food;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }


        return null;
    }
}
