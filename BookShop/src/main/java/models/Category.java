package models;

public class Category {

	private int id;
	private String name;
	private int book_nbr;
	private int deleted;

	public Category() {
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBook_nbr() {
		return book_nbr;
	}

	public void setBook_nbr(int book_nbr) {
		this.book_nbr = book_nbr;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", book_nbr=" + book_nbr + ", deleted=" + deleted + "]";
	}

}
