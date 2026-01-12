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

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no init needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User loginUser = null;
        String loginId = null;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("loginId".equals(ck.getName())) {
                    loginId = ck.getValue();
                    break;
                }
            }
        }

        /* ===== CHƯA ĐĂNG NHẬP ===== */
        if (loginId == null) {
            res.sendRedirect(req.getContextPath() + "/Warning.jsp");
            return;
        }

        /* ===== LẤY USER ===== */
        try {
            loginUser = new UserDAO().getUserById(Integer.parseInt(loginId));
        } catch (Exception e) {
            res.sendRedirect(req.getContextPath() + "/Warning.jsp");
            return;
        }

        /* ===== USER KHÔNG TỒN TẠI ===== */
        if (loginUser == null) {
            res.sendRedirect(req.getContextPath() + "/Warning.jsp");
            return;
        }

        /* ===== KHÔNG PHẢI ADMIN ===== */
        if (loginUser.getRole() != 1) {
            req.setAttribute("userLogin", loginUser);
            req.getRequestDispatcher("/Warning.jsp").forward(req, res);
            return;
        }

        /* ===== LÀ ADMIN ===== */
        req.setAttribute("userLogin", loginUser);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // no destroy needed
    }
}
