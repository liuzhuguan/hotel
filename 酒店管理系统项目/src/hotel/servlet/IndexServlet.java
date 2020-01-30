package hotel.servlet;

import hotel.bean.Apartment;
import hotel.service.ApartmentService;
import hotel.service.ApartmentServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/index.do")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        通过标签name属性值获取前端传过来的参数
        String method = request.getParameter("method");     //此处可能有问题
        String apartmentName = request.getParameter("ApartmentName");
        String apartmentStatus = request.getParameter("ApartmentStatus");

//        查询未使用房间
        ApartmentService apartmentService = new ApartmentServiceImp();
        List<Apartment> apartments = null;

//        不同查询方式
        if (method != null && !method.equals("") && method.equals("SubmitApartment")) {
            apartments = apartmentService.findApartment(apartmentStatus,apartmentName);   //补全一个方法
        } else {
            apartmentStatus = "0";
            apartments = apartmentService.findApartment(apartmentStatus,apartmentName);
        }


        request.setAttribute("apartmentStatus",apartmentStatus);
        request.setAttribute("apartments",apartments);
        request.getRequestDispatcher("/WEB-INF/jsp/app/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
