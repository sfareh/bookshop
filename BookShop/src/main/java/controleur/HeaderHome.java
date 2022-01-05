package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cart;
import models.Category;
import models.CategoryDAO;
import models.Database;

/**
 * Servlet implementation class Header
 */
@WebServlet("/HeaderHome")
public class HeaderHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HeaderHome() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		if (session.getAttribute("isConnected") == null) {
			session.setAttribute("isConnected", false);
		}

		if ((Cart) session.getAttribute("cart") == null) {
			Cart ongoingCart = new Cart();
			session.setAttribute("cart", ongoingCart);
		}

		/* delete book from cart */
		if (request.getParameter("delete") != null) {
			int bookId = Integer.valueOf(request.getParameter("delete"));
			Cart cart = (Cart) session.getAttribute("cart");
			cart.removeItem(bookId);
			session.setAttribute("cart", cart);
			System.out.println((Cart) session.getAttribute("cart"));
			request.getRequestDispatcher("/header_home.jsp").include(request, response);
		}

		/* connection to db */
		Database.Connect();

		/* fetch data */
		CategoryDAO categorydao = new CategoryDAO();
		ArrayList<Category> categories = categorydao.getAll();

		/* send data to jsp */
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/header_home.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
