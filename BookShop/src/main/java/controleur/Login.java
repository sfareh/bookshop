package controleur;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Database;
import models.User;
import models.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns = "/Login", loadOnStartup = 1)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/* connection to db */
		Database.Connect();

		/* test connection */
		boolean isConnected = false;
		boolean messageConnectionNo = false;

		if (request.getParameter("btnConnection") != null) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			///// MD5//////
			String newPassword = password + "w.lK@" + email + (email.length() * 9 / 5);
			String myHash = hashage(newPassword);
			///// MD5//////

			UserDAO userdao = new UserDAO();
			User u = userdao.login(email, myHash);
			System.out.println(u);

			if (u == null) {
				System.out.println("CONNEXION NO");
				messageConnectionNo = true;
			} else {

				HttpSession session = request.getSession(true);
				session.setAttribute("userId", u.getId());
				session.setAttribute("userName", u.getFirstName());
				session.setAttribute("isConnected", true);
				isConnected = true;

				if (request.getParameter("cart") != null) {

					response.sendRedirect("Cart_S");
				} else {
					response.sendRedirect("Index");
				}

				System.out.println("CONNEXION OK");
			}
		}
		request.setAttribute("messageConnectionNo", messageConnectionNo);

		if (!isConnected) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

			System.out.println("Erreur hashage dans servlet Connexion : ");
			e.printStackTrace();
			return null;
		}
	}

}
