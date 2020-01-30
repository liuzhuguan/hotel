package hotel.servlet;

import hotel.bean.Apartment;
import hotel.bean.Food;
import hotel.bean.FoodType;
import hotel.bean.Order;
import hotel.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/app/menu.action")
public class MenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodTypeId = request.getParameter("foodTypeId");
        String id = request.getParameter("id");
        if (id != null)
        {
            request.getSession().setAttribute("id",id);
        }

        //        3.通过客房的id查询出所选择的客房
        ApartmentService apartmentService = new ApartmentServiceImp();
        String sid = request.getSession().getAttribute("id").toString();
        Apartment apartment =  apartmentService.findById(Integer.parseInt(sid));
        request.setAttribute("apartment",apartment);

//        如果有未付款的订单，跳转到详情页面
        OrderService orderService = new OrderServiceImp();
        List<Order> orders = orderService.findByApartmentId(Integer.parseInt(id));
        if (orders != null  &&  orders.size() > 0) {
//            跳到详情页面
            request.setAttribute("orders",orders);
            request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request,response);
        } else {
            //        1.展示未删除的菜系
            FoodTypeService foodTypeService = new FoodTypeServiceImp();
            List<FoodType> foodTypes =  foodTypeService.findAll();

            if (foodTypeId == null || foodTypeId.equals("")) {
//             默认第一个
                Integer foodTypeIdInt = foodTypes.get(0).getId();
                foodTypeId = Integer.toString(foodTypeIdInt);
            }

            FoodService foodService = new FoodServiceImp();
            List<Food> foods = foodService.findByfoodTypeId(Integer.parseInt(foodTypeId));


//        4.展示该房订购菜品
            HttpSession session = request.getSession();
            Map<Integer,Integer> shopCar = (Map<Integer,Integer>)session.getAttribute(id);
            List<Food> foods2 = new ArrayList();    //遍历后把拿到的id对象封装的此处
            Double total = 0.00;        //计算总金额
//        拿到所有菜品的Id
            if (shopCar != null) {
                Set<Integer> foodIds = shopCar.keySet();
                for (Integer foodId : foodIds) {
                    Food food = foodService.findById(foodId);
                    Integer buyNum = shopCar.get(foodId);
                    food.setBuyNum(buyNum);
                    foods2.add(food);

                    Double price = food.getBuyNum() * food.getPrice() * food.getDiscount();
                    total = total + price;
                }
            }

            request.setAttribute("total",total);
            request.setAttribute("foods2",foods2);
            request.setAttribute("foods",foods);
            request.setAttribute("foodTypes",foodTypes);
            request.getRequestDispatcher("/WEB-INF/jsp/app/menu.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
