package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorDAO {

	/* ********* add || update author ********* */
	public void save(Author author) {
		try {
			if (author.getId() != 0) {
				PreparedStatement ps = Database.connection.prepareStatement("UPDATE author SET name=? WHERE id=?");
				ps.setString(1, author.getName());
				ps.setInt(2, author.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connection.prepareStatement("INSERT INTO author(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, author.getName());
				ps.execute();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				author.setId(resultSet.getInt(1));
				System.out.println("New author saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get author by id ********* */

	public Author getById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM author WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet result = ps.executeQuery();

			Author author = new Author();

			if (result.next()) {
				author.setId(result.getInt("id"));
				author.setName(result.getString("name"));
			}
			return author;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all authors ********* */
	public ArrayList<Author> getAll() {

		ArrayList<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM author WHERE deleted=0");

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Author author = new Author();
				author.setId(result.getInt("id"));
				author.setName(result.getString("name"));
				authors.add(author);
			}
			return authors;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete author by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE author SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

	/* ********* get author by keyword ********* */
	public ArrayList<Author> getAuthorByKeyword(String str) {
		ArrayList<Author> authors = new ArrayList<Author>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM author WHERE deleted=0 AND name LIKE ?");
			ps.setString(1, "%" + str + "%");

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Author author = new Author();
				author.setId(result.getInt("id"));
				author.setName(result.getString("name"));
				authors.add(author);
			}
			return authors;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
