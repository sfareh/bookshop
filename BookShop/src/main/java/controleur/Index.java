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
 * Servlet implementation class Index
 */
@WebServlet(urlPatterns = "/Index", loadOnStartup = 1)
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public Index() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* connection to db */
		Database.Connect();

		/* fetch data */
		CategoryDAO categorydao = new CategoryDAO();
		BookDAO bookdao = new BookDAO();
		AuthorDAO authordao = new AuthorDAO();

		ArrayList<Category> categories = categorydao.getAll();
		ArrayList<Book> books = bookdao.getAll();

		ArrayList<Author> authors = authordao.getAll();

		/* send data to jsp */
		request.setAttribute("categories", categories);
		request.setAttribute("books", books);
		request.setAttribute("booksByCategory", books);
		request.setAttribute("authors", authors);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
