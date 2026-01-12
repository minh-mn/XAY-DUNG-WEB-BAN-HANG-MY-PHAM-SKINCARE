package web.java.AdminController.Event;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.CouponDAO;
import web.java.dao.EventDAO;

@WebServlet("/admin/coupon")
public class AdCouponController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdCouponController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        CouponDAO couponDAO = new CouponDAO();
        EventDAO eventDAO = new EventDAO();

        request.setAttribute("coupons", couponDAO.getAllCoupons());
        request.setAttribute("events", eventDAO.getAllEvents());

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
