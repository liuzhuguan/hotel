package hotel.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/app/shopCar.action")
public class ShopCarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String foodId = request.getParameter("foodId");
        String buyNum = request.getParameter("buyNum");
        String apartmentId = request.getParameter("apartmentId");
        Integer foodIdInt = Integer.parseInt(foodId);

        HttpSession session = request.getSession();
//        添加到购物车
        if (method != null && method.equals("add")) {
            //        判断购物车是否为空，便于增删操作
            Map<Integer,Integer> shopCar = (Map<Integer,Integer>)session.getAttribute(apartmentId);

            if (shopCar != null) {
//            购物车中有商品
//            是否已经有当前加入的商品
//            拿到所有已经有的ID
                Set<Integer> foodIds = shopCar.keySet();
                if (foodIds.contains(foodIdInt)) {
                    Integer bugNum2 = shopCar.get(foodIdInt);
                    shopCar.put(foodIdInt,bugNum2+1);
                } else {
                    shopCar.put(foodIdInt,1);
                }

            } else {
                shopCar = new HashMap<>();
                shopCar.put(foodIdInt,1);
                session.setAttribute(apartmentId,shopCar);
            }
            response.sendRedirect(getServletContext().getContextPath() + "/app/menu.action?id=" + apartmentId);
            //request.getRequestDispatcher("/app/menu.action?id=" + apartmentId).forward(request,response);
        } else if (method != null && method.equals("update")) {         //更新购物车
            Map<Integer,Integer> shopCar = (Map<Integer,Integer>)session.getAttribute(apartmentId);
            shopCar.put(foodIdInt,Integer.parseInt(buyNum));

            response.sendRedirect(getServletContext().getContextPath() + "/app/menu.action?id=" + apartmentId);
            //request.getRequestDispatcher("/app/menu.action?id=" + apartmentId).forward(request,response);
        } else if (method != null && method.equals("delete")) {
            Map<Integer,Integer> shopCar = (Map<Integer,Integer>)session.getAttribute(apartmentId);
            shopCar.remove(foodIdInt);
            response.sendRedirect(getServletContext().getContextPath() + "/app/menu.action?id=" + apartmentId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
