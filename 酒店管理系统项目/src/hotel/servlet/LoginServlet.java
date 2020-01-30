package hotel.servlet;

import hotel.bean.User;
import hotel.service.UserService;
import hotel.service.UserServiceImp;
import hotel.util.ConstantUtil;
import hotel.util.CookUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/app/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String method = request.getParameter("method");
        String loginname = request.getParameter("loginname");
        String password = request.getParameter("password");
        String remenber = request.getParameter("remenber");

        if (method != null  &&  method.equals("submitTable")) {
            if (loginname != null && !loginname.equals("")  &&  password != null  &&  !password.equals("") ) {
//                查询数据库，存在且未删除
                UserService userService = new UserServiceImp();
                User user =   userService.findByLoginNameAndPass(loginname,password);

                if (user != null) {
//                    跳到首页
                    HttpSession session = request.getSession();
                    session.setAttribute(ConstantUtil.SESSION_NAME,user);

                    if (remenber != null  &&  remenber.equals("reme")) {
//                        记住密码
                        CookUtils.addCookie(URLEncoder.encode(loginname,"UTF-8"),URLEncoder.encode(password,"UTF-8"),7*24*3600*100,ConstantUtil.COOKIE_NAME,request,response);
                    }

                    response.sendRedirect(getServletContext().getContextPath() + "/app/index.do");
                } else {
                    request.setAttribute("message","用户名或密码错误");
                    request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request,response);
                }

            }
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
