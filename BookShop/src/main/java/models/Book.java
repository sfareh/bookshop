package models;

public class Book extends Author {

	private int id;
	private String title;
	private String image;
	private int author_id;
	private int stock;
	private int category_id;
	private double price;
	private int deleted;

	public Book() {
	}

	public Book(String title, String image, int author_id, int stock, int category_id, double price) {
		this.title = title;
		this.image = image;
		this.author_id = author_id;
		this.stock = stock;
		this.category_id = category_id;
		this.price = price;
	}

	public Book(int id, String title, String image, int author_id, int stock, int category_id, double price) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.author_id = author_id;
		this.stock = stock;
		this.category_id = category_id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", image=" + image + ", author_id=" + author_id + ", stock="
				+ stock + ", category_id=" + category_id + ", price=" + price + ", deleted=" + deleted + "]";
	}

}
