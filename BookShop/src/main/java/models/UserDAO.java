package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

	/* ********* add || update user ********* */
	public void save(User user) {
		try {
			if (user.getId() != 0) {
				PreparedStatement ps = Database.connection
						.prepareStatement("UPDATE user set firstName=?,lastName=?,email=?,password=? where id=?");
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.setInt(5, user.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connection.prepareStatement(
						"INSERT INTO user (firstName,lastName,email,password) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());
				ps.executeUpdate();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				user.setId(resultSet.getInt(1));

				System.out.println("New user saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get user by id ********* */

	public User getById(int id) {
		try {

			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM user WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			User user = new User();

			if (result.next()) {
				user.setId(result.getInt("id"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
			}
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all users ********* */
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<User>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM user WHERE deleted=0");

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				users.add(user);
			}
			return users;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete book by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE user SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

	/* ********* to check if user is a client or an admin ********* */
	public User login(String email, String password) {
		try {

			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM user WHERE deleted=0 AND email=? AND password=? ");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet result = ps.executeQuery();

			User user = new User();

			if (result.next()) {
				user.setId(result.getInt("id"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				return user;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* check if username already exist ********* */
	public static boolean getUsername(String email) {
		String name = "";

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT email FROM user WHERE deleted=0 AND email=?");
			ps.setString(1, email);
			ResultSet result = ps.executeQuery();

			if (result.next())
				name = result.getString("email");

			return email.equals(name) ? true : false;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
