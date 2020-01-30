package hotel.servlet;

import com.sun.org.apache.xpath.internal.operations.Or;
import hotel.bean.Apartment;
import hotel.bean.Order;
import hotel.service.ApartmentService;
import hotel.service.ApartmentServiceImp;
import hotel.service.OrderService;
import hotel.service.OrderServiceImp;
import hotel.util.ConstantUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/app/order.action")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apartmentId = request.getParameter("apartmentId");
        String method = request.getParameter("method");
        String total = request.getParameter("total");
        String orderId = request.getParameter("orderId");
        OrderService orderService = new OrderServiceImp();

        if (method != null && method.equals("order")) {
//            从购物车中将东西放入订单表，详细订单表
            HttpSession session = request.getSession();
            Map<Integer,Integer> shopCar = (Map<Integer,Integer>)session.getAttribute(apartmentId);

            if (shopCar != null && !shopCar.isEmpty()) {

                orderService.order(Integer.parseInt(apartmentId),shopCar,total);

//            删除购物车中东西
                session.removeAttribute(apartmentId);

//                从详情页面查询未删除的订单
//                客房id----菜品表，订单表，详细订单表
                List<Order> orders = orderService.findByApartmentId(Integer.parseInt(apartmentId));
//                当前属于哪一个客房
                ApartmentService apartmentService = new ApartmentServiceImp();
                Apartment apartment = apartmentService.findById(Integer.parseInt(apartmentId));

                request.setAttribute("orders",orders);
                request.setAttribute("apartment",apartment);
//            跳转到订单详情页面
                request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request,response);
            } else  {
                response.sendRedirect(getServletContext().getContextPath() + "/app/menu.action?id=" + apartmentId);
            }
        } else if (method != null  &&  method.equals("pay")) {
            Order order = orderService.findById(Integer.parseInt(orderId));
            order.setOrderStatus(ConstantUtil.ORDER_PAY);


            orderService.pay(order);
            response.sendRedirect(getServletContext().getContextPath() + "/app/index.do");
        } else if (method != null  &&  method.equals("delete")) {
//            取消订单
            Order order = orderService.findById(Integer.parseInt(orderId));

            order.setDisabled(ConstantUtil.DISABLED);

            orderService.deleteOrder(order);
            response.sendRedirect(getServletContext().getContextPath() + "/app/index.do");
        } else if (method != null  &&  method.equals("list")) {
            List<Order> orders = orderService.findAll();        //查询所有订单


            request.setAttribute("orders",orders);
            request.getRequestDispatcher("/WEB-INF/jsp/app/orderList.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
