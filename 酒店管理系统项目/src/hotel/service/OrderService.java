package hotel.service;

import hotel.bean.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void order(int id, Map<Integer, Integer> shopCar, String total);

    List<Order> findByApartmentId(int apartmentId);

    Order findById(int id);

    void pay(Order order);

    void deleteOrder(Order order);

    List<Order> findAll();
}
