package web.java.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.java.dao.BrandDAO;
import web.java.dao.CategoryDAO;
import web.java.dao.CollectionDAO;
import web.java.dao.EventDAO;
import web.java.dao.ProductDAO;
import web.java.dao.UserDAO;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");

	request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
	request.setAttribute("events", new EventDAO().getAllEvent());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("collections", new CollectionDAO().getAllCollection());
	request.setAttribute("flashdeal1s", new EventDAO().getProductInEventRan("3"));
	request.setAttribute("flashdeal2s", new EventDAO().getProductInEventRan("5"));
	request.setAttribute("SkincareProducts", new ProductDAO().getProductSkinCare());
	request.setAttribute("MakeupProducts", new ProductDAO().getProductMakeUp());

	request.getRequestDispatcher("/Page/Profile.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.setContentType("text/html;charset=UTF-8");
	request.setCharacterEncoding("utf-8");

	if (request.getParameter("password").equals("password") == true) {
	    new UserDAO().editUserNotPass(request.getParameter("id"),
		    request.getParameter("email"), request.getParameter("fullname"), request.getParameter("phone"),
		    request.getParameter("avatar"));
	}else {
	    new UserDAO().editUserHavePass(request.getParameter("id"), request.getParameter("password"),
			request.getParameter("email"), request.getParameter("fullname"), request.getParameter("phone"),
			request.getParameter("avatar"));
	}

	request.setAttribute("saleMakeups", new CategoryDAO().getMakeupCate());
	request.setAttribute("events", new EventDAO().getAllEvent());
	request.setAttribute("brands", new BrandDAO().getAllBrand());
	request.setAttribute("categories", new CategoryDAO().getAllCategory());
	request.setAttribute("collections", new CollectionDAO().getAllCollection());
	request.setAttribute("flashdeal1s", new EventDAO().getProductInEventRan("3"));
	request.setAttribute("flashdeal2s", new EventDAO().getProductInEventRan("5"));
	request.setAttribute("SkincareProducts", new ProductDAO().getProductSkinCare());
	request.setAttribute("MakeupProducts", new ProductDAO().getProductMakeUp());
	request.setAttribute("mess", "Success!");

	response.sendRedirect("profile");
    }

}
