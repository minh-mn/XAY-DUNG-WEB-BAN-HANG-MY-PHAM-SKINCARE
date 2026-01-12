package web.java.AdminController.Event;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.CouponDAO;
import web.java.dao.EventDAO;

@WebServlet("/admin/couponDelete")
public class DeleteCouponAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteCouponAdmin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String couponId = request.getParameter("id");

        if (couponId != null && !couponId.isBlank()) {
            CouponDAO couponDAO = new CouponDAO();
            couponDAO.deleteCoupon(Integer.parseInt(couponId));
        }

        request.setAttribute("coupons", new CouponDAO().getAllCoupons());
        request.setAttribute("events", new EventDAO().getAllEvents());

        request.getRequestDispatcher(
                "/Admin/template/management/CouponManagement.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
