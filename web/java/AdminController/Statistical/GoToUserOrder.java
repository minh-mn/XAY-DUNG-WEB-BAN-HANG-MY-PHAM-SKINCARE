package web.java.AdminController.Statistical;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.OrderDAO;

@WebServlet("/admin/dagiao")
public class GoToUserOrder extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GoToUserOrder() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam != null) {
            int orderId = Integer.parseInt(idParam);

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.updateOrderStatus(orderId, 2); // 2 = ĐÃ GIAO / SHIPPING
        }

        response.sendRedirect(
                request.getContextPath() + "/admin/order"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
