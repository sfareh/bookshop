package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceDAO {

	/* ********* add || update invoice ********* */
	public int save(Invoice invoice) {
		try {
			if (invoice.getId() != 0) {
				PreparedStatement ps = Database.connection
						.prepareStatement("UPDATE invoice SET user_id=?,date=? where id=?");
				ps.setInt(1, invoice.getUser_id());
				ps.setDate(2, (java.sql.Date) invoice.getDate());
				ps.setInt(3, invoice.getId());
				ps.executeUpdate();

				return invoice.getId();
			} else {
				PreparedStatement ps = Database.connection.prepareStatement(
						"INSERT INTO invoice(user_id, date) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, invoice.getUser_id());
				ps.setDate(2, invoice.getDate());
				ps.executeUpdate();

				ResultSet resultSet = ps.getGeneratedKeys();
				resultSet.next();
				invoice.setId(resultSet.getInt(1));

				System.out.println("New invoice saved into database");
				return invoice.getId();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT SAVED");
			return -1;
		}
	}

	/* ********* get invoice by id ********* */

	public Invoice getById(int id) {
		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM invoice WHERE deleted=0 AND id=?");
			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();

			Invoice invoice = new Invoice();

			if (resultSet.next()) {
				invoice.setId(resultSet.getInt("id"));
				invoice.setUser_id(resultSet.getInt("user_id"));
				invoice.setDate(resultSet.getDate("date"));
			}
			return invoice;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* get all invoices ********* */
	public ArrayList<Invoice> getAll() {
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();

		try {
			PreparedStatement ps = Database.connection.prepareStatement("SELECT * FROM invoice WHERE deleted=0");

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt("id"));
				invoice.setUser_id(resultSet.getInt("user_id"));
				invoice.setDate(resultSet.getDate("date"));
				invoices.add(invoice);
			}
			return invoices;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/* ********* delete invoice by id ********* */
	public void deleteById(int id) {
		try {
			PreparedStatement ps = Database.connection.prepareStatement("UPDATE invoice SET deleted=1 WHERE id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
			System.out.println("DELETED OK");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("NOT DELETED");
		}
	}

	/* ********* get all invoices by userId ********* */
	public ArrayList<Invoice> getAll(int user_id) {
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();

		try {
			PreparedStatement ps = Database.connection
					.prepareStatement("SELECT * FROM invoice WHERE deleted=0 AND user_id=?");
			ps.setInt(1, user_id);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt("id"));
				invoice.setUser_id(resultSet.getInt("user_id"));
				invoice.setDate(resultSet.getDate("date"));
				invoices.add(invoice);
			}
			return invoices;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
