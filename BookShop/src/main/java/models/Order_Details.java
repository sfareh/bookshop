package models;

public class Order_Details {

	private int id;
	private int book_id;
	private int quantity;
	private int invoice_id;
	private int deleted;

	public Order_Details() {
	}

	public Order_Details(int book_id, int quantity, int invoice_id) {
		this.book_id = book_id;
		this.quantity = quantity;
		this.invoice_id = invoice_id;
	}

	public Order_Details(int id, int book_id, int quantity, int invoice_id) {
		this.id = id;
		this.book_id = book_id;
		this.quantity = quantity;
		this.invoice_id = invoice_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Order_Details [id=" + id + ", book_id=" + book_id + ", quantity=" + quantity + ", invoice_id="
				+ invoice_id + ", deleted=" + deleted + "]";
	}

}
