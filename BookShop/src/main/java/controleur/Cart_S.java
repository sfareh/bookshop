package controleur;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Cart;
import models.CartItem;
import models.Database;
import models.Invoice;
import models.InvoiceDAO;
import models.Order_Details;
import models.Order_DetailsDAO;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart_S")
public class Cart_S extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart_S() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean invoiceDone = false;

		/* connection to db */
		Database.Connect();

		/* fetch data */

		if (request.getParameter("update") != null) {
			HttpSession session = request.getSession(true);
			Cart cart = (Cart) session.getAttribute("cart");
			String[] quantity = request.getParameterValues("quantity");

			for (int i = 0; i < cart.items.size(); i++) {
				if (Integer.valueOf(quantity[i]) == 0)
					cart.items.remove(i);
				else
					cart.items.get(i).setQuantity(Integer.valueOf(quantity[i]));
			}
			session.setAttribute("cart", cart);
		}

		/* delete book from cart */
		if (request.getParameter("delete") != null) {
			HttpSession session = request.getSession(true);
			int bookId = Integer.valueOf(request.getParameter("delete"));
			Cart cart = (Cart) session.getAttribute("cart");
			cart.removeItem(bookId);
			session.setAttribute("cart", cart);
			System.out.println((Cart) session.getAttribute("cart"));
		}

		/* check out */
		if (request.getParameter("valider") != null) {
			HttpSession session = request.getSession(true);

			Cart cart = (Cart) session.getAttribute("cart");

			int userId = (int) session.getAttribute("userId");

			long millis = System.currentTimeMillis();

			Date date = new Date(millis);

			// save invoice in db
			InvoiceDAO invoicedao = new InvoiceDAO();
			Invoice invoice = new Invoice(userId, date);
			int invoiceId = invoicedao.save(invoice);
			System.out.println("facture id : " + invoiceId);

			// save each order in db
			Order_DetailsDAO detailsdao = new Order_DetailsDAO();
			for (CartItem item : cart.items) {
				Order_Details order = new Order_Details();
				order.setBook_id(item.getBook().getId());
				order.setQuantity(item.getQuantity());
				order.setInvoice_id(invoiceId);
				detailsdao.save(order);
			}
			cart.emptycart();

			session.setAttribute("cart", cart);

			invoiceDone = true;
			response.sendRedirect("CheckOut");
		} else {
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
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
