package web.java.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import web.java.dao.UserDAO;
import web.java.model.User;

@WebFilter("/*")
public class CheckCookie implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no init needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        /* ===== BỎ QUA CÁC PATH KHÔNG CẦN CHECK ===== */
        if (path.startsWith(req.getContextPath() + "/admin")
                || path.startsWith(req.getContextPath() + "/signout")) {
            chain.doFilter(request, response);
            return;
        }

        /* ===== CHECK COOKIE ===== */
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("loginId".equals(ck.getName())) {
                    try {
                        int userId = Integer.parseInt(ck.getValue());
                        User user = new UserDAO().getUserById(userId);

                        if (user != null) {
                            req.setAttribute("userLogin", user);
                            req.setAttribute("userLoginId", userId);
                        }
                    } catch (NumberFormatException e) {
                        // cookie giả → bỏ qua
                    }
                    break;
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no destroy needed
    }
}
