package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Order_DetailsDAO {

	/* ********* add || update Order_details ********* */
	public void save(Order_Details order_details) {
		try {
			if (order_details.getId() != 0) {
				PreparedStatement ps = Database.connection
						.prepareStatement("UPDATE order_details SET book_id=?,quantity=?,invoice_id=? where id=?");
				ps.setInt(1, order_details.getBook_id());
				ps.setInt(2, order_details.getQuantity());
				ps.setInt(3, order_details.getInvoice_id());
				ps.setInt(4, order_details.getId());
				ps.executeUpdate();
			} else {
				PreparedStatement ps = Database.connection.prepareStatement(
						"INSERT INTO order_details (book_id,quantity,invoice_id) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, order_details.getBook_id());
				ps.setInt(2, order_details.getQuantity());
				ps.setInt(3, order_details.getInvoice_id());
				ps.execute();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				order_details.setId(resultSet.getInt(1));

				System.out.println("New order saved into database");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
		}
	}

	/* ********* get Order_details by id ********* */

	public Order_Details getById(int id) {
		try {

			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM order_details WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			Order_Details order_details = new Order_Details();

			if (resultSet.next()) {
				order_details.setId(resultSet.getInt("id"));
				order_details.setBook_id(resultSet.getInt("book_id"));
				order_details.setQuantity(resultSet.getInt("quantity"));
				order_details.setInvoice_id(resultSet.getInt("invoice_id"));
			}
			return order_details;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all order_details ********* */
	public ArrayList<Order_Details> getAll() {
		ArrayList<Order_Details> orders_details = new ArrayList<Order_Details>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM order_details WHERE deleted=0");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Order_Details order_details = new Order_Details();
				order_details.setId(resultSet.getInt("id"));
				order_details.setBook_id(resultSet.getInt("book_id"));
				order_details.setQuantity(resultSet.getInt("quantity"));
				order_details.setInvoice_id(resultSet.getInt("invoice_id"));
				orders_details.add(order_details);
			}
			return orders_details;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete order_details by id ********* */
	public static void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("UPDATE order_details SET deleted=1 WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

}
