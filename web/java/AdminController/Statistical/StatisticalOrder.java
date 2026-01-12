package web.java.AdminController.Statistical;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import web.java.dao.OrderDAO;
import web.java.model.Order;
import web.java.utils.ConnectDB;

@WebServlet("/admin/order")
public class StatisticalOrder extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public StatisticalOrder() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        OrderDAO orderDAO = new OrderDAO();

        try (Connection conn = ConnectDB.getConnection()) {
            List<Order> orders = orderDAO.getAllOrders(conn);
            request.setAttribute("orders", orders);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.getRequestDispatcher(
                "/Admin/template/statistical/OrderStatistical.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
