package models;

public class Stock_Entry {

	private int id;
	private int book_id;
	private int quantity;
	private int deleted;

	public Stock_Entry() {

	}

	public Stock_Entry(int book_id, int quantity) {
		this.book_id = book_id;
		this.quantity = quantity;
	}

	public Stock_Entry(int id, int book_id, int quantity) {
		this.id = id;
		this.book_id = book_id;
		this.quantity = quantity;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Stock_Entry [id=" + id + ", book_id=" + book_id + ", quantity=" + quantity + ", deleted=" + deleted
				+ "]";
	}

}
