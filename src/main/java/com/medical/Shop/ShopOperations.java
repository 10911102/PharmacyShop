package com.medical.Shop;

import java.util.List;
import java.util.Scanner;

import com.medical.Pharmacy.Medicine;
import com.medical.Pharmacy.Order;
import com.medical.Pharmacy.Pharmacy;

public class ShopOperations {
	protected static List<Pharmacy> list;
	protected static List<Order> placedOrders;
	private OrderOperations orderOperation;
	Scanner sc = new Scanner(System.in);
	

	public List<Pharmacy> getList() {
		return list;
	}

	public static void setList(List<Pharmacy> list) {
		ShopOperations.list = list;
	}

	public List<Order> getPlacedOrders() {
		return placedOrders;
	}

	public void setPlacedOrders(List<Order> placedOrders) {
		ShopOperations.placedOrders = placedOrders;
	}

	/**
	 * Prints the list of medicine with there quantity available in pharmacy store
	 */
	public void showAllMedicine() {
		int count = 1;
		System.out.println("Medicine_Id         Name        Brand      Qauntity");
		for (Pharmacy pharmacy : getList()) {
			System.out.println(count++ + " " + pharmacy + "  " + pharmacy.getQuantity());
		}
	}

	/**
	 * Search the medicine name or brand available in store
	 * @param medicine String medicine name or brand
	 * @return if found then returns the object otherwise returns null with message on console.
	 */
	public Medicine searchMedicine(String medicine) {
		for (Pharmacy pharmacy : list) {
			if (pharmacy.getMedicine().getName().equalsIgnoreCase(medicine)) {
				return pharmacy.getMedicine();
			} else if (pharmacy.getMedicine().getBrand().equalsIgnoreCase(medicine)) {
				return pharmacy.getMedicine();
			}
		}
		System.out.println("Try another medicine!");
		return null;
	}

	/**
	 * To Sell medicine to customer when he visits the store.
	 */
	public void sellMedicine() {
		showAllMedicine();
		System.out.println("Enter Medicine_Id to sell medicine");
		int id = sc.nextInt();
		sc.nextLine();
		Pharmacy pharmacy = list.get(--id);
		System.out.println("Enter quantity");
		int quantity = sc.nextInt();
		checkStock(pharmacy, quantity);
		System.out.println("Order Placed for " + pharmacy + " " + quantity);
		System.out.println("Enter any number to continue or 0 to end order");
		int choice = sc.nextInt();
		if (choice != 0) {
			sellMedicine();
		}
	}

	/**
	 * To Check the stock that parameterized medicine is available or not before selling them.
	 * warns the shopkeeper if stock is less then 25.
	 * @param pharmacy that contains medicine details and quantity available in stock.
	 * @param quantity number of medicine required by customer
	 */
	public void checkStock(Pharmacy pharmacy, int quantity) {
		if (pharmacy.getQuantity() > quantity) {
			pharmacy.setQuantity(pharmacy.getQuantity() - quantity);
			if ((pharmacy.getQuantity() - 20) < quantity) {
				System.out.println("Stock running out. pls order stock");
			}
		} else {
			System.out.println("Shortage in Stock.Tell to visit again");
			System.out.println("Enter Quantity of medicine " + pharmacy.getMedicine().getName() + " to place order");
			quantity = sc.nextInt();
			orderOperation.placeOrder(pharmacy, quantity);
		}
	}

	public OrderOperations getOrderOperation() {
		return orderOperation;
	}

	public void setOrderOperation(OrderOperations orderOperation) {
		this.orderOperation = orderOperation;
	}

}
