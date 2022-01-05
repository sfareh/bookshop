package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Stock_EntryDAO {

	/* ********* add || update stock_entry ********* */
	public void save(Stock_Entry stock_entry) {
		try {
			if (stock_entry.getId() != 0) {
				PreparedStatement ps = Database.connection
						.prepareStatement("UPDATE stock_entry SET book_id=?,quantity=? where id=?");
				ps.setInt(1, stock_entry.getBook_id());
				ps.setInt(2, stock_entry.getQuantity());
				ps.setInt(3, stock_entry.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connection
						.prepareStatement("INSERT INTO order_details (book_id,quantity) VALUES(?,?)");
				ps.setInt(1, stock_entry.getBook_id());
				ps.setInt(2, stock_entry.getQuantity());
				ps.executeUpdate();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				stock_entry.setId(resultSet.getInt(1));

				System.out.println("New stock entry saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get stock_entry by id ********* */
	public Stock_Entry getById(int id) {
		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM stock_entry WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			Stock_Entry stock_entry = new Stock_Entry();

			if (resultSet.next()) {
				stock_entry.setId(resultSet.getInt("id"));
				stock_entry.setBook_id(resultSet.getInt("book_id"));
				stock_entry.setQuantity(resultSet.getInt("quantity"));
			}
			return stock_entry;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all stock_entry ********* */
	public ArrayList<Stock_Entry> getAll() {
		ArrayList<Stock_Entry> stock_entries = new ArrayList<Stock_Entry>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM stock_entry WHERE deleted=0");

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Stock_Entry stock_entry = new Stock_Entry();
				stock_entry.setId(result.getInt("id"));
				stock_entry.setBook_id(result.getInt("book_id"));
				stock_entry.setQuantity(result.getInt("quantity"));
				stock_entries.add(stock_entry);
			}
			return stock_entries;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete stock_entry by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE stock_entry SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}
}
