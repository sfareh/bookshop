package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Book;
import models.BookDAO;
import models.Category;
import models.CategoryDAO;
import models.Database;

/**
 * Servlet implementation class CategoryS
 */
@WebServlet("/CategoryS")
public class CategoryS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryS() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int start;

		System.out.println("pagination : " + request.getParameter("pagination"));

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

		ArrayList<Category> categories = categorydao.getAll();

		int catId;
		if (request.getParameter("catId") != null)
			catId = Integer.valueOf(request.getParameter("catId"));
		else
			catId = 0;

		int nbrBook = bookdao.getBookCountByCatId(catId);

		int nbrPage = (nbrBook / 12) + 1;

		String cartegoryName = categorydao.getById(catId).getName();
		ArrayList<Book> bookByCat = bookdao.getBookByCategoryId(catId, start, total);
		System.out.println("bookByCat : " + bookByCat);

		System.out.println("nbrPage : " + nbrPage);

		/* send data to jsp */
		request.setAttribute("bookByCat", bookByCat);
		request.setAttribute("categories", categories);
		request.setAttribute("cartegoryName", cartegoryName);
		request.setAttribute("nbrPage", nbrPage);

		request.getRequestDispatcher("/category.jsp").forward(request, response);
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
