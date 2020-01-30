package hotel.servlet;

import hotel.bean.Food;
import hotel.bean.FoodType;
import hotel.service.FoodService;
import hotel.service.FoodServiceImp;
import hotel.service.FoodTypeService;
import hotel.service.FoodTypeServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/menuList.do")
public class MenuListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String foodTypeId = request.getParameter("foodTypeId");

//        查询所有未删除菜品
        FoodTypeService foodTypeService = new FoodTypeServiceImp();
        List<FoodType> foodTypes =  foodTypeService.findAll();

        if (foodTypeId == null || foodTypeId.equals("")) {
//             默认第一个
            Integer foodTypeIdInt = foodTypes.get(0).getId();
            foodTypeId = Integer.toString(foodTypeIdInt);
        }
        FoodService foodService = new FoodServiceImp();
        List<Food> foods = foodService.findByfoodTypeId(Integer.parseInt(foodTypeId));

        request.setAttribute("foods",foods);
        request.setAttribute("foodTypes",foodTypes);
        request.getRequestDispatcher("/WEB-INF/jsp/app/menuList.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
