package hotel.service;

import hotel.bean.Apartment;
import hotel.bean.Order;
import hotel.bean.OrderDetail;
import hotel.dao.ApartmentDao;
import hotel.dao.ApartmentDaoImp;
import hotel.dao.OrderDao;
import hotel.dao.OrderDaoImp;

import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImp implements OrderService {
    private OrderDao orderDao = new OrderDaoImp();

    @Override
    public void order(int id, Map<Integer, Integer> shopCar, String total) {
        Order order = new Order();

        StringBuffer orderCode = new StringBuffer();
        orderCode.append("Ascott-");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        orderCode.append(simpleDateFormat.format(new Date()));
        orderCode.append("UID.randUID().toString()");


        order.setRoomId(id);
        order.setTotalPrice(Double.valueOf(total));
        order.setOrderCode(orderCode.toString());

        orderDao.order(order,shopCar);
    }

    @Override
    public List<Order> findByApartmentId(int apartmentId) {
        List<Order> orders = orderDao.findByApartmentId(apartmentId);

        if (orders != null  &&  orders.size() > 0 ) {
            for(Order order : orders) {
                List<OrderDetail> details = orderDao.findByOrderId(order.getId());

                if (details != null  &&  details.size() > 0 ) {
                    order.setOrderDetails(details);
                }
            }
        }
        return orders;
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void pay(Order order) {
        orderDao.pay(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orderDao.deleteOrder(order);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = orderDao.findAll();

        if (orders != null  &&  orders.size() > 0 ) {
            for(Order order : orders) {
                List<OrderDetail> details = orderDao.findByOrderId(order.getId());

                if (details != null  &&  details.size() > 0 ) {
                    order.setOrderDetails(details);
                }
                ApartmentDao apartmentDao = new ApartmentDaoImp();
                Apartment apartment = apartmentDao.findById(order.getRoomId());

                order.setApartment(apartment);
            }
        }
        return orders;
    }
}
