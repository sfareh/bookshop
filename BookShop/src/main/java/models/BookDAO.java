package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO {
	/* ********* add || update book ********* */
	public void save(Book book) {
		try {
			if (book.getId() != 0) {
				PreparedStatement ps = Database.connection.prepareStatement(
						"UPDATE book SET title=?,image=?,author_id=?,stock=?,category_id=?,price=? WHERE id=?");
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getImage());
				ps.setInt(3, book.getAuthor_id());
				ps.setInt(4, book.getStock());
				ps.setInt(5, book.getCategory_id());
				ps.setDouble(6, book.getPrice());
				ps.setInt(7, book.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connection.prepareStatement(
						"INSERT INTO book (title,image,author_id,stock,category_id,price) VALUES(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getImage());
				ps.setInt(3, book.getAuthor_id());
				ps.setInt(4, book.getStock());
				ps.setInt(5, book.getCategory_id());
				ps.setDouble(6, book.getPrice());
				ps.executeUpdate();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				book.setId(resultSet.getInt(1));

				System.out.println("New book saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get book by id ********* */
	public Book getById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM book WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			Book book = new Book();

			if (resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
			}
			return book;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books ********* */
	public ArrayList<Book> getAll() {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement(
					"SELECT book.id,title,image,author_id,stock,category_id,price,name FROM book,author WHERE book.deleted=0 AND book.author_id=author.id");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				book.setName(resultSet.getString("name"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books pagination********* */
	/* start = from index data, total = nbr of data at a time */
	public ArrayList<Book> getAllPagination(int start, int total) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM book WHERE deleted=0 LIMIT " + (start - 1) + "," + total);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				books.add(book);
			}
			System.out.println(ps.toString());
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete book by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE book SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

	/* ********* get book by keyword ********* */
	public ArrayList<Book> getBookByKeyword(String str) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM book WHERE deleted=0 AND title LIKE ?");
			ps.setString(1, "%" + str + "%");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books by price range ********* */
	public ArrayList<Book> getBookByPriceRange(int nbr, int nbr1) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM book WHERE deleted=0 AND price>=? AND price<=?");
			ps.setInt(1, nbr);
			ps.setInt(2, nbr1);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books by author ********* */
	public ArrayList<Book> getBookByAuthorName(String name) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement(
					"SELECT * FROM book WHERE deleted=0 AND author_id in(SELECT id FROM author WHERE name LIKE ?)");
			ps.setString(1, "%" + name + "%");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books by category name ********* */
	public ArrayList<Book> getBookByCategoryName(String str) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement(
					"SELECT book.id,title,image,author_id,stock,category_id,price,name FROM book,author WHERE book.deleted=0 AND book.author_id=author.id AND category_id in(SELECT id FROM category WHERE name LIKE ?)");
			ps.setString(1, "%" + str + "%");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				book.setName(resultSet.getString("name"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all books by category id ********* */
	public ArrayList<Book> getBookByCategoryId(int id, int start, int total) {
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement(
					"SELECT book.id,title,image,author_id,stock,category_id,price,name FROM book,author WHERE book.deleted=0 AND category_id=? AND book.author_id=author.id LIMIT "
							+ (start - 1) + "," + total);
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setImage(resultSet.getString("image"));
				book.setAuthor_id(resultSet.getInt("author_id"));
				book.setStock(resultSet.getInt("stock"));
				book.setCategory_id(resultSet.getInt("category_id"));
				book.setPrice(resultSet.getFloat("price"));
				book.setName(resultSet.getString("name"));
				books.add(book);
			}
			return books;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get book count by category id ********* */
	public int getBookCountByCatId(int id) {
		int nbr = 0;
		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT COUNT(*) FROM book WHERE category_id=?");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();

			if (resultSet.next())
				nbr = resultSet.getInt(1);

			System.out.println("COUNT OK");

			return nbr;

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("COUNT NO");

			return -1;
		}
	}
}
