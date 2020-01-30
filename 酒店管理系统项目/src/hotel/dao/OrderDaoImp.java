package hotel.dao;

import hotel.bean.Apartment;
import hotel.bean.Food;
import hotel.bean.Order;
import hotel.bean.OrderDetail;
import hotel.util.ConstantUtil;
import hotel.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDaoImp implements OrderDao {

    @Override
    public void order(Order order, Map<Integer, Integer> shopCar) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            //②准备SQL
            String sql = "INSERT INTO hotel_order (order_code,room_id,total_Price,order_Date) VALUES(?,?,?,NOW())";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,order.getOrderCode());
            preparedStatement.setInt(2,order.getRoomId());
            preparedStatement.setDouble(3,order.getTotalPrice());

            int rows = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            generatedKeys.next();   //省略开头
            Integer orderId = generatedKeys.getInt(1);

//            保存订单明细



            Set<Integer> foodIds = shopCar.keySet();
            for (Integer foodId : foodIds) {

                Integer buyNum = shopCar.get(foodId);
                String sqlItem = "INSERT INTO order_detail (orderId,food_id,buyNum,buy_discount) VALUES(?,?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sqlItem);
//                先拿到购买数量

                FoodDao foodDao = new FoodDaoImp();
                Food food = foodDao.findById(foodId);
                preparedStatement1.setInt(1,orderId);
                preparedStatement1.setInt(2,foodId);
                preparedStatement1.setInt(3,buyNum);
                preparedStatement1.setDouble(4,food.getDiscount());

                preparedStatement1.executeUpdate();
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }


    }

    @Override
    public List<Order> findByApartmentId(int apartmentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            //②准备SQL
            String sql = "SELECT * FROM hotel_order WHERE disabled = 0 AND room_id = ? AND order_Status = 0";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,apartmentId);

            resultSet = preparedStatement.executeQuery();   //遍历该房未付款订单
            List<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("order_code"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setTotalPrice(resultSet.getDouble("total_Price"));
                order.setDisabled(resultSet.getInt("disabled"));
                order.setOrderDate(resultSet.getTimestamp("order_Date"));
                order.setOrderStatus(resultSet.getInt("order_Status"));
                order.setPayDate(resultSet.getTimestamp("pay_date"));

                orders.add(order);
            }
                return  orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }
        return null;
    }

    @Override
    public List<OrderDetail> findByOrderId(Integer orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            //②准备SQL
            String sql = "SELECT order_detail.`id` detailId, order_detail.*, hotel_food.*  FROM order_detail " +
                    " LEFT JOIN hotel_food ON hotel_food.`id` = order_detail.`food_id` " +
                    " WHERE order_detail.`orderId` = ? AND order_detail.`disabled` = 0";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderId);

            resultSet = preparedStatement.executeQuery();   //遍历该房未付款订单
            List<OrderDetail> orderDetails = new ArrayList<>();
            while(resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setBuyNum(resultSet.getInt("buyNum"));
                orderDetail.setFoodId(resultSet.getInt("food_id"));
                orderDetail.setOrderId(resultSet.getInt("orderId"));

                Food food = new Food();
                food.setDiscount(resultSet.getDouble("discount"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setFoodTypeId(resultSet.getInt("foodType_id"));
                food.setPrice(resultSet.getDouble("price"));
                food.setImg(resultSet.getString("img"));
                food.setRemark(resultSet.getString("remark"));

                orderDetail.setFood(food);
                orderDetails.add(orderDetail);

            }
            return orderDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Order findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            //②准备SQL
            String sql = "SELECT * FROM hotel_order WHERE id = ?";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();   //遍历该房未付款订单

            while(resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("order_code"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setTotalPrice(resultSet.getDouble("total_Price"));
                order.setDisabled(resultSet.getInt("disabled"));
                order.setOrderDate(resultSet.getTimestamp("order_Date"));
                order.setOrderStatus(resultSet.getInt("order_Status"));
                order.setPayDate(resultSet.getTimestamp("pay_date"));

                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void pay(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            //②准备SQL
            String sql = "UPDATE hotel_order SET order_code = ?, room_id = ?, total_Price = ?, order_Status = ?,update_date = ?, order_Date = NOW(),pay_date = NOW(), disabled = ? WHERE id = ?";

            Timestamp updateDate = order.getUpdateDate() != null ? new Timestamp(order.getUpdateDate().getTime()) : null;
            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getOrderCode());
            preparedStatement.setInt(2,order.getRoomId());
            preparedStatement.setDouble(3,order.getTotalPrice());
            preparedStatement.setInt(4,order.getOrderStatus());
            preparedStatement.setTimestamp(5,updateDate);
            preparedStatement.setInt(6,order.getDisabled());
            preparedStatement.setInt(7,order.getId());

            int rows = preparedStatement.executeUpdate();

//            付款改状态
            ApartmentDao apartmentDao = new ApartmentDaoImp();
            Apartment apartment = apartmentDao.findById(order.getRoomId());
            apartment.setApartment_status(0);
            apartment.setUpdate_date(new java.util.Date());

            apartmentDao.update(apartment);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }

    }

    @Override
    public void deleteOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            //②准备SQL
            String sql = "UPDATE hotel_order SET disabled = ?,update_date = NOW() WHERE id = ?";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,order.getDisabled());
            preparedStatement.setInt(2,order.getId());

            int rows = preparedStatement.executeUpdate();

//            取消订单
            ApartmentDao apartmentDao = new ApartmentDaoImp();
            Apartment apartment = apartmentDao.findById(order.getRoomId());
            apartment.setApartment_status(ConstantUtil.ROOM_NO_USE);
            apartment.setUpdate_date(new java.util.Date());

            apartmentDao.update(apartment);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }

    }

    @Override
    public List<Order> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();
            //②准备SQL
            String sql = "SELECT * FROM hotel_order ORDER BY order_Date DESC";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();   //遍历该房未付款订单
            List<Order> orders = new ArrayList<>();
            while(resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderCode(resultSet.getString("order_code"));
                order.setRoomId(resultSet.getInt("room_id"));
                order.setTotalPrice(resultSet.getDouble("total_Price"));
                order.setDisabled(resultSet.getInt("disabled"));
                order.setOrderDate(resultSet.getTimestamp("order_Date"));
                order.setOrderStatus(resultSet.getInt("order_Status"));
                order.setPayDate(resultSet.getTimestamp("pay_date"));

                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }


        return null;
    }
}
