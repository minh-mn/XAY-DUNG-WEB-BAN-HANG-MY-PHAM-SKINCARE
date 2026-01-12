package web.java.AdminController.Event;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.CouponDAO;
import web.java.model.Coupon;

@WebServlet("/admin/couponAdd")
public class AddCouponController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddCouponController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String code = request.getParameter("coupon");
        String discountStr = request.getParameter("discount");
        String quantityStr = request.getParameter("number");
        String startStr = request.getParameter("startDate");
        String endStr = request.getParameter("endDate");

        if (code == null || code.isEmpty()
                || discountStr == null || quantityStr == null) {
            response.sendRedirect(
                    request.getContextPath() + "/admin/coupon?error=invalid"
            );
            return;
        }

        Coupon c = new Coupon();
        c.setCode(code);
        c.setDiscountPercent(Integer.parseInt(discountStr));
        c.setQuantity(Integer.parseInt(quantityStr));

        if (startStr != null && !startStr.isEmpty()) {
            c.setStartDate(LocalDateTime.parse(startStr));
        }
        if (endStr != null && !endStr.isEmpty()) {
            c.setEndDate(LocalDateTime.parse(endStr));
        }

        new CouponDAO().addCoupon(c);

        response.sendRedirect(
                request.getContextPath() + "/admin/coupon"
        );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(
                request.getContextPath() + "/admin/coupon"
        );
    }
}
