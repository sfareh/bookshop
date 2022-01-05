package models;

import java.sql.Date;

public class Invoice {

	private int id;
	private int user_id;
	private Date date;
	private int deleted;

	public Invoice() {
	}

	public Invoice(int user_id, Date date) {
		this.user_id = user_id;
		this.date = date;
	}

	public Invoice(int id, int user_id, Date date) {
		this.id = id;
		this.user_id = user_id;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", user_id=" + user_id + ", date=" + date + ", deleted=" + deleted + "]";
	}

}
