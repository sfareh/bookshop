package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAO {

	/* ********* add || update category ********* */
	public void save(Category category) {
		try {
			if (category.getId() != 0) {
				PreparedStatement ps = Database.connection.prepareStatement("UPDATE category SET name=? WHERE id=?");
				ps.setString(1, category.getName());
				ps.setInt(2, category.getId());
				ps.executeUpdate();

			} else {
				PreparedStatement ps = Database.connection.prepareStatement("INSERT INTO category (name) VALUES(?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, category.getName());
				ps.executeUpdate();

				ResultSet resultSetSet = ps.getGeneratedKeys();
				resultSetSet.next();
				category.setId(resultSetSet.getInt(1));
				System.out.println("New category saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get category by id ********* */
	public Category getById(int id) {
		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM category WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			Category category = new Category();

			if (resultSet.next()) {
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				category.setBook_nbr(resultSet.getInt("book_nbr"));
			}
			return category;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all categories ********* */
	public ArrayList<Category> getAll() {
		ArrayList<Category> categories = new ArrayList<Category>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM category WHERE deleted=0");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				category.setBook_nbr(resultSet.getInt("book_nbr"));
				categories.add(category);
			}
			return categories;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete category by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE category SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

	/* ********* get category by keyword ********* */
	public ArrayList<Category> getCategoryByKeyword(String str) {
		ArrayList<Category> categories = new ArrayList<Category>();

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM category WHERE deleted=0 AND name LIKE ?");
			ps.setString(1, "%" + str + "%");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setName(resultSet.getString("name"));
				category.setBook_nbr(resultSet.getInt("book_nbr"));
				categories.add(category);
			}
			return categories;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
