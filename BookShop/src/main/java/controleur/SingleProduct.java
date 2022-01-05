package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Author;
import models.AuthorDAO;
import models.Book;
import models.BookDAO;
import models.Cart;
import models.CartItem;
import models.Category;
import models.CategoryDAO;
import models.Database;

/**
 * Servlet implementation class SingleProduct
 */
@WebServlet(urlPatterns = "/SingleProduct", loadOnStartup = 1)
public class SingleProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SingleProduct() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		System.out.println("id book : " + id);

		/* connection to db */
		Database.Connect();

		/* fetch data */
		CategoryDAO categorydao = new CategoryDAO();
		BookDAO bookdao = new BookDAO();
		AuthorDAO authordao = new AuthorDAO();

		ArrayList<Category> categories = categorydao.getAll();

		Book book = bookdao.getById(id);
		Author author = authordao.getById(book.getAuthor_id());

		Category cat = categorydao.getById(book.getCategory_id());
		ArrayList<Book> bookByCat = bookdao.getBookByCategoryName(cat.getName());

		/* send data to jsp */
		request.setAttribute("categories", categories);
		request.setAttribute("book", book);
		request.setAttribute("author", author);
		request.setAttribute("bookByCat", bookByCat);

		// AJOUTER AU PANIER
		if (request.getParameter("btnAddCart") != null) {

			HttpSession session = request.getSession(true);

			int quantity = Integer.valueOf(request.getParameter("quantity"));
			CartItem item = new CartItem(book, quantity);

			Cart cart = (Cart) session.getAttribute("cart");
			cart.addItem(item);

			session.setAttribute("cart", cart);

			System.out.println("SingleProduct servlet cart : " + (Cart) session.getAttribute("cart"));
		}

		request.getRequestDispatcher("/single-product.jsp").forward(request, response);
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
