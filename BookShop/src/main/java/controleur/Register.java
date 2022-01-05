package controleur;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Database;
import models.User;
import models.UserDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet(urlPatterns = "/Register", loadOnStartup = 1)
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* connection to db */
		Database.Connect();

		boolean registered = false;

		if (request.getParameter("btnRegister") != null) {

			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			///// MD5//////
			String newPassword = password + "w.lK@" + email + (email.length() * 9 / 5);
			String myHash = hashage(newPassword);
			///// MD5//////

			UserDAO userdao = new UserDAO();
			User u = new User(firstName, lastName, email, myHash);
			userdao.save(u);

			registered = true;

			request.getRequestDispatcher("/Login").forward(request, response);

			System.out.println("INSCRIPTION OK");
		}
		request.setAttribute("registered", registered);

		if (!registered) {
			request.getRequestDispatcher("/register.jsp").forward(request, response);
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

	private String hashage(String str) {
		StringBuffer sb = new StringBuffer();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			}
			System.out.println("Password en format hexa : " + sb.toString());

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {

			System.out.println("Erreur hashage dans servlet Register : ");
			e.printStackTrace();
			return null;
		}
	}
}
