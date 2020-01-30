package hotel.servlet;

import hotel.bean.User;
import hotel.service.UserService;
import hotel.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/register.do")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        String loginName = request.getParameter("loginName");
        String  phone = request.getParameter("phone");
        String  email = request.getParameter("email");
        String  passWord = request.getParameter("passWord");
        String  okPassWord = request.getParameter("okPassWord");

        UserService userService = new UserServiceImp();
        if (method != null  &&  method.equals("ajaxLoginName")) {
//            校验用户输入登录名存在否

            User user = userService.findByLoginName(loginName);

            if (user != null ) {
//                用户名已存在
                response.setCharacterEncoding("utf-8");
                response.getWriter().print("用户名已存在");
            }

        } else if (method != null  &&  method.equals("submitTable")) {
            if (passWord != null  &&  !passWord.equals(okPassWord)) {
                request.setAttribute("message","两次输入密码不一致");
                request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request,response);
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(passWord);
                user.setLoginName(loginName);

                userService.save(user);
                response.sendRedirect(getServletContext().getContextPath()+ "/app/login.do");
            }
        }
        else {
            request.getRequestDispatcher("/WEB-INF/jsp/app/register.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
