package models;

import java.util.ArrayList;

public class Cart {

	public ArrayList<CartItem> items = new ArrayList<CartItem>();

	public Cart() {

	}

	public Cart(ArrayList<CartItem> items) {
		this.items = items;
	}

	public int count() {
		return this.items.size();
	}

	public void addItem(CartItem item) {
		boolean exist = false;
		for (CartItem i : items) {
			if (i.getBook().getId() == item.getBook().getId()) {
				exist = true;
				i.setQuantity(i.getQuantity() + item.getQuantity());
			}
		}
		if (!exist) {
			items.add(item);
		}
	}

	public void removeItem(int itemId) {
		CartItem product = new CartItem();
		for (CartItem item : items) {
			if (item.getBook().getId() == itemId) {
				product = item;
			}
		}
		items.remove(product);
	}

	public double totalAmount() {
		double total = 0;
		for (CartItem item : items) {
			total += item.getBook().getPrice() * item.getQuantity();
		}
		return total;
	}

	public void emptycart() {
		items = new ArrayList<CartItem>();
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}

}
