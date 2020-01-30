package hotel.servlet;

import hotel.util.ConstantUtil;
import hotel.util.CookUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/loginout.action")
public class LoginOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        退出时清空
        HttpSession session = request.getSession();
        session.removeAttribute(ConstantUtil.SESSION_NAME);

        CookUtils.removeCookie(ConstantUtil.COOKIE_NAME,request,response);

        request.getRequestDispatcher("/app/login.do").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
