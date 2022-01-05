package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Author;
import models.AuthorDAO;
import models.Book;
import models.BookDAO;
import models.Category;
import models.CategoryDAO;
import models.Database;

/**
 * Servlet implementation class ShopGrid
 */
@WebServlet(urlPatterns = "/ShopGrid", loadOnStartup = 1)
public class ShopGrid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopGrid() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int start = 0;

		if (request.getParameter("pagination") != null)
			start = Integer.valueOf(request.getParameter("pagination"));
		else
			start = 1;

		int total = 12;

		if (start > 1) {
			start = start - 1;
			start = start * total + 1;
		}

		System.out.println("start : " + start);
		
		/* connection to db */
		Database.Connect();

		/* fetch data */

		CategoryDAO categorydao = new CategoryDAO();
		BookDAO bookdao = new BookDAO();
		AuthorDAO authordao = new AuthorDAO();

		ArrayList<Category> categories = categorydao.getAll();
		ArrayList<Book> books = bookdao.getAll();

		ArrayList<Author> authors = authordao.getAll();

		int nbrPage = (books.size() / 12) + 1;

		System.out.println("nbrPage : " + nbrPage);

		/* send data to jsp */
		request.setAttribute("categories", categories);
		request.setAttribute("books", books);
		request.setAttribute("authors", authors);

		ArrayList<Book> booksPagination = bookdao.getAllPagination(start, total);

		System.out.println("booksPagination servlet : " + booksPagination.size());
		request.setAttribute("booksPagination", booksPagination);
		request.setAttribute("nbrPage", nbrPage);
		request.getRequestDispatcher("/shop-grid.jsp").forward(request, response);
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
