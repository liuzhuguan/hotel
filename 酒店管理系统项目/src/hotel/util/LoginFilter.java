package hotel.util;
import hotel.bean.User;
import hotel.service.UserService;
import hotel.service.UserServiceImp;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * 拦截所有以.action结尾的请求
 */
@WebFilter("*.action")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("=====拦截器=======.action==");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        //获取保存在session中的user
        User user = (User) httpServletRequest.getSession().getAttribute(ConstantUtil.SESSION_NAME);

        if(user == null) {
            //session 中没有用户信息
            //查看是否有保存用户名和密码的cookie
            Cookie cookie = CookUtils.getCookieByName(ConstantUtil.COOKIE_NAME, httpServletRequest);

            if(cookie != null) {
                //获取cookie的value值
                String value = URLDecoder.decode(cookie.getValue(), "utf-8");
                //分割用户和密码
                String[]  loginNameAndPass = value.split("#");
                System.out.println("loginNameAndPass:"+loginNameAndPass[0]+"  "+loginNameAndPass[1]);

                //根据用户名和密码查看保存在cookie的用户信息是否有效
                UserService userService = new UserServiceImp();
                User user2 = userService.findByLoginNameAndPass(loginNameAndPass[0], loginNameAndPass[1]);
                System.out.println("user2:"+user2);

                if(user2 != null) {
                    //说明保存在cookie中的用户信息有效
                    //保存用户信息在session
                    httpServletRequest.getSession().setAttribute(ConstantUtil.SESSION_NAME, user2);

                    //cookie中的信息有效，放行，执行器本身对应的方法
                    chain.doFilter(request, response);
                }else {
                    //登录不成功
                    request.setAttribute("message", "请先登录！");
                    //跳转到登录页面
                    request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
                }
            }else {
                //登录不成功
                request.setAttribute("message", "请先登录！");
                //跳转到登录页面
                request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
            }
        }else {
            //用户已经登陆，放行，执行器本身对应的方法
            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}