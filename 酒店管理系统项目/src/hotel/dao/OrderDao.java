package hotel.dao;

import hotel.bean.Order;
import hotel.bean.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    void order(Order order, Map<Integer, Integer> shopCar);

    List<Order> findByApartmentId(int apartmentId);

    List<OrderDetail> findByOrderId(Integer orderId);

    Order findById(int id);

    void pay(Order order);

    void deleteOrder(Order order);

    List<Order> findAll();
}
