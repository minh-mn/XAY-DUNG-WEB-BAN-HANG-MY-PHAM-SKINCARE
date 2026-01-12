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
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.UserDAO;
import web.java.model.User;

@WebFilter({"/login", "/signup"})
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no init needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("loginId".equals(ck.getName())) {
                    try {
                        int userId = Integer.parseInt(ck.getValue());
                        User user = new UserDAO().getUserById(userId);

                        /* ===== ĐÃ ĐĂNG NHẬP ===== */
                        if (user != null) {
                            res.sendRedirect(req.getContextPath() + "/home");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        // cookie giả → bỏ qua
                    }
                }
            }
        }

        /* ===== CHƯA ĐĂNG NHẬP ===== */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no destroy needed
    }
}
