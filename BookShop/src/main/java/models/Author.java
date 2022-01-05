package models;

public class Author {

	private int id;
	private String name;
	private int deleted;

	public Author() {
	}

	public Author(String name) {
		this.name = name;
	}

	public Author(int id, String name) {
		this.id = id;
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

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", deleted=" + deleted + "]";
	}

}
