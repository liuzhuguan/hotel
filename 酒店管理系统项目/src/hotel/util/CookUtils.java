package hotel.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookUtils {
//    添加cookie
    public static void addCookie(String loginname, String password, int age, String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieByName(cookieName,request);

        if (cookie != null) {
            cookie.setValue(loginname + "#" + password);
        } else {
            cookie = new Cookie(cookieName,loginname + "#" + password);
        }

        cookie.setMaxAge(age);
        cookie.setPath(request.getContextPath());

        response.addCookie(cookie);
    }

    public static Cookie getCookieByName(String cookieName, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie cookie2 : cookies) {
                if (cookie2.getName().equals(cookieName)) {
                    return cookie2;
                }
            }
        }
        return cookie;
    }

    public static void removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookieByName(cookieName,request);

        if (cookie != null ) {
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
    }
}
