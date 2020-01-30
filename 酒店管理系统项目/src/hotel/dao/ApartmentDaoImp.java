package hotel.dao;

import hotel.bean.Apartment;
import hotel.util.ConnectionFactory;
import hotel.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImp implements ApartmentDao{
    @Override
    public List<Apartment> findApartment(String apartmentStatus, String apartmentName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            StringBuffer sql = new StringBuffer("SELECT * FROM bighotel_apartment WHERE disabled = 0 " );

            if(apartmentName != null && !apartmentName.equals("")) {
                sql.append(" and apartment_name like '%"+ apartmentName +"%' ");
            }


            if(apartmentStatus != null && !apartmentStatus.equals("")) {
                sql.append(" and apartment_status = "+ apartmentStatus);
            }


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql.toString());

            resultSet = preparedStatement.executeQuery();

            List<Apartment> apartments = new ArrayList<Apartment>();
            while (resultSet.next()) {          //省略表头信息
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getInt("id"));
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                apartment.setApartment_status(resultSet.getInt("apartment_status"));
                apartment.setApartment_id(resultSet.getInt("apartment_id"));
                apartment.setPrice(resultSet.getDouble("price"));
                apartment.setDiscount(resultSet.getDouble("discount"));
                apartment.setRemark(resultSet.getString("remark"));
                apartment.setDisabled(resultSet.getInt("disabled"));
                apartment.setCreate_date(resultSet.getDate("create_date"));
                apartment.setUpdate_date(resultSet.getDate("update_date"));

                apartments.add(apartment);

            }
            return apartments;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Apartment findById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "SELECT * FROM bighotel_apartment WHERE disabled = 0 AND id = ?";


            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {          //省略表头信息
                Apartment apartment = new Apartment();

                apartment.setId(resultSet.getInt("id"));
                apartment.setApartment_name(resultSet.getString("apartment_name"));
                apartment.setApartment_status(resultSet.getInt("apartment_status"));
                apartment.setApartment_id(resultSet.getInt("apartment_id"));
                apartment.setPrice(resultSet.getDouble("price"));
                apartment.setDiscount(resultSet.getDouble("discount"));
                apartment.setRemark(resultSet.getString("remark"));
                apartment.setDisabled(resultSet.getInt("disabled"));
                apartment.setCreate_date(resultSet.getDate("create_date"));
                apartment.setUpdate_date(resultSet.getDate("update_date"));

                return apartment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void update(Apartment apartment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //①获取连接
            connection = JDBCUtils.getConnection();

            //②准备SQL
            String sql = "UPDATE bighotel_apartment SET apartment_name = ? ," +
                    " apartment_status = ? , price = ? , discount = ? , remark = ? ," +
                    " create_date = ? ,update_date = ? , disabled = ? WHERE id = ?;";

                Date createDate = apartment.getCreate_date() != null ? new Date(apartment.getCreate_date().getTime()) : null;
                Date updateDate = apartment.getUpdate_date() != null ? new Date(apartment.getUpdate_date().getTime()) : null;

            //③ 获取集装箱
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,apartment.getApartment_name());
            preparedStatement.setInt(2,apartment.getApartment_status());
            preparedStatement.setDouble(3,apartment.getPrice());
            preparedStatement.setDouble(4,apartment.getDiscount());
            preparedStatement.setString(5,apartment.getRemark());
            preparedStatement.setDate(6,createDate);
            preparedStatement.setDate(7,updateDate);
            preparedStatement.setInt(8,apartment.getDisabled());
            preparedStatement.setInt(9,apartment.getId());


            int rows = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement,connection);
        }
    }
}
