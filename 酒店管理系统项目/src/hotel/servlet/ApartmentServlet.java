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

@WebServlet("/app/apartment.action")
public class ApartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apartmentId = request.getParameter("apartmentId");
        String apartmentStatus = request.getParameter("apartmentStatus");

//        通过客房id查询客房
        ApartmentService apartmentService = new ApartmentServiceImp();
        Apartment apartment = apartmentService.findById(Integer.parseInt(apartmentId));
        apartment.setApartment_status(Integer.parseInt(apartmentStatus));
        System.out.println(apartment);
//        更改传入对象数据库值
        apartmentService.update(apartment);
        System.out.println(apartment);

        response.sendRedirect(getServletContext().getContextPath() + "/app/menu.action?id=" + apartmentId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
